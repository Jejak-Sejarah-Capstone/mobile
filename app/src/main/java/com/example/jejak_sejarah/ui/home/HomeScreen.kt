import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import coil.compose.rememberImagePainter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.api.RetrofitClient
import com.example.jejak_sejarah.model.predict.ApiResponse
import com.example.jejak_sejarah.model.predict.ListItem
import com.example.jejak_sejarah.model.predict.LocationRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jejak_sejarah.ui.theme.darkBrown
import com.example.jejak_sejarah.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: HomeViewModel = viewModel()
    var items by remember { mutableStateOf(viewModel.items) }
    var errorMessage by remember { mutableStateOf(viewModel.errorMessage) }
    var isLoading by remember { mutableStateOf(false) } // State untuk loading

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var latitude by remember { mutableStateOf<Double?>(null) }
    var longitude by remember { mutableStateOf<Double?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ListItem?>(null) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                getLastLocation(fusedLocationClient) { lat, long ->
                    latitude = lat
                    longitude = long
                    if (latitude != null && longitude != null) {
                        isLoading = true // Set loading true
                        viewModel.fetchItems(latitude!!.toString(), longitude!!.toString()) {
                            isLoading = false // Set loading false setelah fetch
                        }
                    }
                }
            } else {
                viewModel.errorMessage = "Izin lokasi tidak diberikan"
            }
        }
    )

    // Mengamati perubahan pada items dan errorMessage
    LaunchedEffect(viewModel.items) {
        items = viewModel.items
        errorMessage = viewModel.errorMessage
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .align(Alignment.TopCenter),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 240.dp)
        ) {
            ElevatedButton(
                onClick = {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(270.dp, 50.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B4513))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        modifier = Modifier.size(40.dp).padding(end = 8.dp)
                    )
                    Text(
                        text = "Bacaan Terdekat",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.padding(top = 30.dp).align(Alignment.CenterHorizontally), color = darkBrown)
            } else if (items.isEmpty()) {
                Text(
                    text = "Belum ada bacaan terdekat.",
                    color = Color.LightGray,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp).padding(30.dp)
                )
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(items) { item ->
                        ListItemView(item) {
                            selectedItem = item
                            showDialog = true
                        }
                    }
                }
            }

            // Tampilkan pesan kesalahan jika ada
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (showDialog && selectedItem != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { selectedItem!!.judul_peristiwa?.let { Text(text = it) } },
                text = {
                    Box(modifier = Modifier.width(400.dp)) { // Atur lebar AlertDialog
                        LazyColumn {
                            item {
                                Image(
                                    painter = rememberImagePainter(selectedItem!!.gambar_pendukung),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxWidth().height(150.dp).padding(bottom = 10.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            item {
                                Text(
                                    text = "${selectedItem!!.deskripsi}",
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    textAlign = TextAlign.Justify
                                )
                            }
                            item {
                                Text(text = "Tanggal: ${selectedItem!!.tanggal_peristiwa}")
                            }
                            item {
                                Text(text = "Lokasi: ${selectedItem!!.Lokasi}")
                            }
                            item {
                                Text(text = "Kategori: ${selectedItem!!.kategori_sejarah}")
                            }
                        }
                    }
                },
                confirmButton = {
                    ElevatedButton(onClick = { showDialog = false }) {
                        Text("OK")
                    }
                }
            )
        }

    }
}

@Composable
fun ListItemView(item: ListItem, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFD2B48C)
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(item.gambar_pendukung),
                contentDescription = item.judul_peristiwa,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.weight(1f)) {
                item.judul_peristiwa?.let {
                    Text(
                        text = it,
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                }
                item.deskripsi?.let {
                    Text(
                        text = it,
                        style = TextStyle(fontSize = 14.sp),
                        maxLines = 1,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

private fun getLastLocation(
    fusedLocationClient: FusedLocationProviderClient,
    onLocationReceived: (Double, Double) -> Unit
) {
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location: Location? ->
            if (location != null) {
                onLocationReceived(location.latitude, location.longitude)
            } else {
                Log.e("HomeScreen", "Lokasi tidak tersedia")
            }
        }
        .addOnFailureListener { exception ->
            Log.e("HomeScreen", "Gagal mendapatkan lokasi: ${exception.message}")
        }
}

package com.example.jejak_sejarah.ui.leaderboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejak_sejarah.ui.theme.darkBrown
import com.example.jejak_sejarah.ui.theme.lightBrown

@Composable
fun LeaderboardScreen(modifier: Modifier = Modifier) {
    Column(modifier =
    Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()))
    {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.leaderboard),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.TopCenter),
                contentScale = ContentScale.Crop
            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .offset(y = (-30).dp)
            .padding(bottom = 10.dp), horizontalArrangement = Arrangement.SpaceEvenly){
            Column ( modifier = Modifier.padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Replace with actual image
                )
                Image(
                   painter = painterResource(id = R.drawable.no2),
                    contentDescription = "number 2",
                    modifier = Modifier
                        .size(50.dp)
                )
                Text(style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = darkBrown ,text = "nama 2")
                OutlinedCard(
                    border = BorderStroke(2.dp, darkBrown),
                    shape = RoundedCornerShape(50),
                    ){
                    Text(style = MaterialTheme.typography.bodyMedium ,modifier = Modifier.padding(8.dp), text = "ini point")
                }
            }
            Column (Modifier,horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.crown),
                    contentDescription = "crown",
                    modifier = Modifier
                        .padding(top = 0.dp)
                        .size(75.dp)
                        .offset(y = (15).dp) // Menggeser gambar mahkota ke atas
                )
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Replace with actual image
                )
                Image(
                    painter = painterResource(id = R.drawable.no1),
                    contentDescription = "number 1",
                    modifier = Modifier
                        .size(50.dp)
                )
                Text(style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = darkBrown ,text = "Muhammad Amali")
                OutlinedCard(
                    border = BorderStroke(2.dp, darkBrown),
                    shape = RoundedCornerShape(50),
                ){
                    Text(style = MaterialTheme.typography.bodyMedium ,modifier = Modifier.padding(8.dp), text = "ini point")
                }
            }
            Column ( modifier = Modifier.padding(top = 100.dp), horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Replace with actual image
                )
                Image(
                    painter = painterResource(id = R.drawable.no3),
                    contentDescription = "number 3",
                    modifier = Modifier
                        .size(50.dp)
                )
                Text(style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, color = darkBrown ,text = "nama 2")
                OutlinedCard(
                    border = BorderStroke(2.dp, darkBrown),
                    shape = RoundedCornerShape(50),
                ){
                    Text(style = MaterialTheme.typography.bodyMedium ,modifier = Modifier.padding(8.dp), text = "ini point")
                }
            }
        }

        val items = (4..10).toList() // Daftar angka dari 4 hingga 10
        Column(modifier = Modifier.offset(y = (-30).dp)
        ) {
            items.forEach { index ->
                    ItemCard(index)
            }
        }
        Rank()
    Spacer(modifier = Modifier.height(80.dp)) // Sesuaikan dengan tinggi navbar Anda
    }
}

@Composable
fun ItemCard(index:Int){
    ElevatedCard( colors = CardDefaults.cardColors(
        containerColor = darkBrown
    ),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween, verticalAlignment = Alignment.CenterVertically)  {
            Row(modifier = Modifier
                .padding(10.dp)
                .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(end = 8.dp), text = "#${index}")
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(Color.Gray) // Replace with actual image
                )
                Text(color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp), text = "Nama")
            }

            Text(color = Color.White, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(end= 10.dp), text = "2000 Point")

        }
    }
}

@Composable
fun Rank(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .offset(y = (-30).dp)
        .padding(top = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape) // Membuat bentuk bulat
                .background(Color.Transparent) // Latar belakang transparan
                .border(BorderStroke(2.dp, darkBrown), CircleShape) // Outline dengan warna dan ketebalan
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .size(15.dp) // Ukuran titik
                .clip(CircleShape) // Membuat bentuk bulat
                .background(Color.Transparent) // Latar belakang transparan
                .border(BorderStroke(3.dp, darkBrown), CircleShape) // Outline dengan warna dan ketebalan
        )
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .size(25.dp) // Ukuran titik
                .clip(CircleShape) // Membuat bentuk bulat
                .background(Color.Transparent) // Latar belakang transparan
                .border(BorderStroke(4.dp, darkBrown), CircleShape) // Outline dengan warna dan ketebalan
        )
        ItemCard(index = 11)
    }
}

@Preview(showBackground = true)
@Composable
fun LeaderboardScreenPreview() {
    JejaksejarahTheme {
        LeaderboardScreen(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()))
    }
}
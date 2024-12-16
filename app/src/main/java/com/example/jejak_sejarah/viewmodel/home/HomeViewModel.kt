package com.example.jejak_sejarah.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jejak_sejarah.api.RetrofitClient
import com.example.jejak_sejarah.model.predict.ApiResponse
import com.example.jejak_sejarah.model.predict.ListItem
import com.example.jejak_sejarah.model.predict.LocationRequest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var items: List<ListItem> = emptyList()
        private set

    var errorMessage: String? = null

    fun fetchItems(lat: String, long: String, onComplete: () -> Unit) {
        val apiService = RetrofitClient.apiService
        val request = LocationRequest(lat, long)

        apiService.getItems(request).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        items = it.nearest_points.map { point ->
                            ListItem(
                                latitude = point.latitude,
                                longitude = point.longitude,
                                judul_peristiwa = point.judul_peristiwa ?: "Judul tidak tersedia",
                                deskripsi = point.deskripsi ?: "Deskripsi tidak tersedia",
                                tanggal_peristiwa = point.tanggal_peristiwa ?: "Tanggal tidak tersedia",
                                Lokasi = point.Lokasi ?: "Lokasi tidak tersedia",
                                gambar_pendukung = point.gambar_pendukung ?: "",
                                kategori_sejarah = point.kategori_sejarah ?: "Kategori tidak tersedia",
                                distance = point.distance
                            )
                        }
                    }
                } else {
                    errorMessage = "Gagal mendapatkan data dari server"
                }
                onComplete() // Panggil callback setelah selesai
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                errorMessage = "Kesalahan: ${t.message}"
                onComplete() // Panggil callback setelah gagal
            }
        })
    }
}

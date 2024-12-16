package com.example.jejak_sejarah.model.predict
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val nearest_points: List<ListItem>
)

data class ListItem(
    val latitude: Double,
    val longitude: Double,

    @SerializedName("judul peristiwa")
    val judul_peristiwa: String?, // Nullable jika bisa null

    @SerializedName("deskripsi")
    val deskripsi: String?,

    @SerializedName("tanggal peristiwa")
    val tanggal_peristiwa: String?,

    @SerializedName("Lokasi")
    val Lokasi: String?,

    @SerializedName("gambar pendukung")
    val gambar_pendukung: String?,

    @SerializedName("kategori sejarah")
    val kategori_sejarah: String?,

    val distance: Double
)



data class LocationRequest(
    val latitude: String,
    val longitude: String
)
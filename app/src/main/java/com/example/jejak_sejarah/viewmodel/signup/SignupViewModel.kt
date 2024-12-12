package com.example.jejak_sejarah.viewmodel.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jejak_sejarah.api.RetrofitClient
import com.example.jejak_sejarah.model.signup.SignupRequest
import com.example.jejak_sejarah.model.signup.SignupResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SignupViewModel : ViewModel() {
    var signupResponse: SignupResponse? = null
    var errorMessage: String? = null

    fun signup(name: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                // Membuat request signup
                val response = RetrofitClient.apiService.signup(SignupRequest(name, email, password))
                signupResponse = response // Menyimpan respons

                // Anda bisa memeriksa respons di sini
                onResult(true, null) // Panggil callback dengan true jika berhasil
            } catch (e: HttpException) {
                errorMessage = e.message() // Tangani kesalahan HTTP
                onResult(false, errorMessage) // Panggil callback dengan false dan pesan error
            } catch (e: Exception) {
                errorMessage = e.localizedMessage // Tangani kesalahan umum
                onResult(false, errorMessage) // Panggil callback dengan false dan pesan error
            }
        }
    }
}

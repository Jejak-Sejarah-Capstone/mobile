package com.example.jejak_sejarah.api

import com.example.jejak_sejarah.model.signup.SignupRequest
import com.example.jejak_sejarah.model.signup.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): SignupResponse
}

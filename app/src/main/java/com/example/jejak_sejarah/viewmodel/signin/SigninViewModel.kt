package com.example.jejak_sejarah.viewmodel.signin

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SigninViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signin(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null) // Login berhasil
                } else {
                    callback(false, task.exception?.message) // Login gagal
                }
            }
    }
}

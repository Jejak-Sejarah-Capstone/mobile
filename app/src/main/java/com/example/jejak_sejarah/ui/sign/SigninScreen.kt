package com.example.jejak_sejarah.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.theme.darkBrown
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.jejak_sejarah.viewmodel.signin.SigninViewModel

@Composable
fun SigninScreen(navController: NavController, viewModel: SigninViewModel, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showToast by remember { mutableStateOf(false) }
    var toastMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo_app_new),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Silahkan masuk",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                EmailTextField(email) { email = it }
                PasswordTextField(password) { password = it }
                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    CircularProgressIndicator(color = darkBrown)
                } else {
                    LoginButton(modifier, navController) {
                        if (email.isBlank() || password.isBlank()) {
                            errorMessage = "Semua field harus diisi."
                            return@LoginButton
                        }
                        isLoading = true
                        viewModel.signin(email, password) { success, message ->
                            isLoading = false
                            if (success) {
                                toastMessage = "Login berhasil!"
                                showToast = true
                                // Navigasi ke layar utama atau dashboard
                                navController.navigate("main") {
                                    popUpTo("signin") { inclusive = true }
                                }
                            } else {
                                errorMessage = "Login Gagal, silakan coba lagi."
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("signup") }) {
                    Text("Belum punya akun? Daftar")
                }
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }

    // Menampilkan Toast jika showToast bernilai true
    if (showToast) {
        LaunchedEffect(toastMessage) {
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
            showToast = false // Reset state setelah menampilkan Toast
        }
    }
}

@Composable
fun EmailTextField(email: String, onEmailChange: (String) -> Unit) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text("Email") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkBrown,
        )
    )
}

@Composable
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text("Password") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkBrown,
        ),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun LoginButton(modifier: Modifier = Modifier, navController: NavController, onLoginClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Button(
            onClick = { onLoginClick() },
            colors = ButtonDefaults.buttonColors(containerColor = darkBrown),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Masuk")
        }
    }
}

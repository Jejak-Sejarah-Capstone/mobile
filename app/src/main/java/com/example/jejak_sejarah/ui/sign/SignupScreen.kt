package com.example.jejak_sejarah.ui.sign

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejak_sejarah.ui.theme.darkBrown
import com.example.jejak_sejarah.viewmodel.signup.SignupViewModel

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignupViewModel, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
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
                    "Silahkan daftar",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                NameTextField(name) { name = it }
                EmailTextField(email) { email = it }
                PasswordTextField(password) { password = it }
                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    CircularProgressIndicator(color = darkBrown)
                } else {
                    SignupButton(modifier, navController) {
                        if (name.isBlank() || email.isBlank() || password.isBlank()) {
                            errorMessage = "Semua field harus diisi."
                            return@SignupButton
                        }
                        isLoading = true
                        viewModel.signup(name, email, password) { success, message ->
                            isLoading = false
                            if (success) {
                                showToast("Pendaftaran berhasil!", context)
                                navController.navigate("signin") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            } else {
                                errorMessage = "Signup Gagal, silakan coba lagi."
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("signin") }) {
                    Text("Sudah punya akun? Sign In")
                }
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

fun showToast(message: String, context: android.content.Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
fun NameTextField(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text("Username") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = darkBrown,
        )
    )
}

@Composable
fun SignupButton(modifier: Modifier = Modifier, navController: NavController, onSignupClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Button(
            onClick = { onSignupClick() },
            colors = ButtonDefaults.buttonColors(containerColor = darkBrown),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Daftar")
        }
    }
}

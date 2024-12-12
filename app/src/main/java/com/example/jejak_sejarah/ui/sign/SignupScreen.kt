package com.example.jejak_sejarah.ui.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejak_sejarah.ui.theme.darkBrown

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }

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
                UsernameTextField(username) { username = it }
                EmailTextField(email) { email = it }
                PasswordTextField(password) { password = it }
                Spacer(modifier = Modifier.height(16.dp))
                SignupButton(modifier, navController)
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("signin") }) {
                    Text("Sudah punya akun? Sign In")
                }
            }
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
        )
    )
}

@Composable
fun UsernameTextField(username: String, onUsernameChange: (String) -> Unit) {
    OutlinedTextField(
        value = username,
        onValueChange = onUsernameChange,
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
fun SignupButton(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Button(
            onClick = { navController.navigate("signin") },
            colors = ButtonDefaults.buttonColors(containerColor = darkBrown),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Daftar")
        }
    }
}

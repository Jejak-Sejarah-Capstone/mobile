package com.example.jejak_sejarah.ui.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejak_sejarah.ui.theme.darkBrown

@Composable
fun SignInScreen(navController: NavController, modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_logo_app_new),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    "Selamat Datang!",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Silahkan masuk."
                )
                EmailTextField(modifier)
                PasswordTextField(modifier)
                LoginButton(modifier, navController)
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { navController.navigate("signup") }) {
                    Text("Belum punya akun? Sign Up")
                }
            }
        }
    }
}

@Composable
fun EmailTextField(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkBrown,
            )
        )
    }
}

@Composable
fun PasswordTextField(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = darkBrown,
            )
        )
    }
}

@Composable
fun LoginButton(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(16.dp),
    ) {
        Button(
            onClick = { navController.navigate("main") },
            colors = ButtonDefaults.buttonColors(containerColor = darkBrown),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
        ) {
            Text("Masuk")
        }
    }
}

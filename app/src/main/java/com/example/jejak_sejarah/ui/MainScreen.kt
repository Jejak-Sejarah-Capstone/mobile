package com.example.jejak_sejarah.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.jejak_sejarah.ui.navigation.AppNavigation
import com.example.jejak_sejarah.ui.navigation.BottomNavigationBar
import com.example.jejak_sejarah.ui.theme.darkBrown

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "JEJAK SEJARAH", fontWeight= FontWeight.Bold, color = Color.White)
                },
                backgroundColor = darkBrown
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        AppNavigation(navController = navController, modifier = Modifier.padding(innerPadding).padding(top = 24.dp))
    }
}

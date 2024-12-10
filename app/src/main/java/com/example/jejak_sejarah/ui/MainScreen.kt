package com.example.jejak_sejarah.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.jejak_sejarah.ui.navigation.AppNavigation

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavigation(navController = navController)
}
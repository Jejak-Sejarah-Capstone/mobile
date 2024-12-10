package com.example.jejak_sejarah.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jejak_sejarah.ui.home.HomeScreen
import com.example.jejak_sejarah.ui.leaderboard.LeaderboardScreen
import com.example.jejak_sejarah.ui.navigation.BottomNavigationBar
import com.example.jejak_sejarah.ui.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    // Scaffold dengan bottom bar (Navbar)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }, // Navbar di bawah
        content = { paddingValues ->
            // Menambahkan padding agar konten tidak tertutup navbar
            NavHost(navController = navController, startDestination = "home") {
                // Menambahkan rute untuk setiap halaman
                composable("home") { HomeScreen(modifier = Modifier.padding(paddingValues)) }
                composable("leaderboard") { LeaderboardScreen(modifier = Modifier.padding(paddingValues)) }
                composable("profile") { ProfileScreen(modifier = Modifier.padding(paddingValues)) }
            }
        }
    )
}

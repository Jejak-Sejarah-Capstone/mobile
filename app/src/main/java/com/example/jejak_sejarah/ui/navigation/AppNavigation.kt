package com.example.jejak_sejarah.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jejak_sejarah.ui.home.HomeScreen
import com.example.jejak_sejarah.ui.leaderboard.LeaderboardScreen
import com.example.jejak_sejarah.ui.profile.ProfileScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier:Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) },
        content = { paddingValues ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeScreen(modifier = Modifier.padding(paddingValues)) }
                composable("leaderboard") { LeaderboardScreen(modifier = Modifier.padding(paddingValues)) }
                composable("profile") { ProfileScreen(modifier = Modifier.padding(paddingValues)) }
            }
        }
    )
}

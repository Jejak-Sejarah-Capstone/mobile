package com.example.jejak_sejarah.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("home", "leaderboard", "profile")
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, route ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = when (index) {
                            0 -> Icons.Default.Home
                            1 -> Icons.Default.Assessment
                            2 -> Icons.Filled.AccountCircle
                            else -> Icons.Default.Home
                        },
                        contentDescription = route
                    )
                },
                label = { Text(route.capitalize()) },
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        // Agar tidak terjadi penumpukan di stack
                        popUpTo("home") { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

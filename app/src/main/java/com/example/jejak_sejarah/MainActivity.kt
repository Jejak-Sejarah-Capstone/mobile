package com.example.jejak_sejarah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jejak_sejarah.ui.MainScreen
import com.example.jejak_sejarah.ui.sign.SignInScreen
import com.example.jejak_sejarah.ui.sign.SignUpScreen
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejaksejarah.ui.sign.splashscreen.SplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JejaksejarahTheme {
                AppNavHost()
            }
        }
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    var isSplashScreenVisible by remember { mutableStateOf(true) }

    NavHost(navController, startDestination = if (isSplashScreenVisible) "splash" else "signin") {
        composable("splash") {
            SplashScreen(onTimeout = {
                isSplashScreenVisible = false
                navController.navigate("signin") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("signin") { SignInScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("main") { MainScreen() }
    }
}

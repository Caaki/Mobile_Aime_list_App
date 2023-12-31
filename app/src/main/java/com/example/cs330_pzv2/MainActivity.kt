package com.example.cs330_pzv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cs330_pzv2.presentation.Screen
import com.example.cs330_pzv2.presentation.anime_details_page.components.AnimeDetailsItemScreen
import com.example.cs330_pzv2.presentation.anime_main_page.find_anime.components.AnimeMainPageScreen
import com.example.cs330_pzv2.presentation.watched_anime_details.AnimeDatabaseDetailScreen
import com.example.cs330_pzv2.ui.theme.CS330PZv2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CS330PZv2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AnimeMainPage.route
                    ) {
                        composable(
                            route = Screen.AnimeMainPage.route
                        ) {
                            AnimeMainPageScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AnimeDetailsScreen.route+ "/{animeId}"
                        ){
                            AnimeDetailsItemScreen(navController = navController)
                        }
                        composable(
                            route = Screen.AnimeDatabaseDetailScreen.route +"/{animeId}"
                        ){
                            AnimeDatabaseDetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}


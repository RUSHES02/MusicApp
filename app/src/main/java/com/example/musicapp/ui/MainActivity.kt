package com.example.musicapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.services.MediaService
import com.example.musicapp.ui.home.HomeBottomPlayer
import com.example.musicapp.ui.home.HomeEvent
import com.example.musicapp.ui.home.HomeScreen
import com.example.musicapp.ui.home.viewModel.AlbumViewModel
import com.example.musicapp.ui.navigation.Destination
import com.example.musicapp.ui.song.SongScreen
import com.example.musicapp.ui.song.viewModel.SongViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.theme.bg_color
import com.example.musicapp.ui.viewModel.SharedViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MusicAppTheme {
                val systemUiController = rememberSystemUiController()

                systemUiController.setSystemBarsColor(
                    color = bg_color, // Replace with your desired color
                    darkIcons  = false // Set to true if you want dark icons
                )

                systemUiController.setNavigationBarColor(
                    color = bg_color,
                    darkIcons = false
                )

                sharedViewModel = hiltViewModel()
                MusicApp(
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        sharedViewModel.destroyMediaController()
        stopService(Intent(this, MediaService::class.java))
    }
}


@Composable
fun MusicApp(
    modifier: Modifier,
    sharedViewModel: SharedViewModel
){
    val navController = rememberNavController()
    val musicControllerUiState = sharedViewModel.musicControllerUiState
    NavHost(navController = navController, startDestination = Destination.home) {
        composable(route = Destination.home) {
            val albumViewModel: AlbumViewModel = hiltViewModel()
            val isInitialized = rememberSaveable { mutableStateOf(false) }
            if (!isInitialized.value) {
                LaunchedEffect(key1 = Unit) {
                    albumViewModel.onEvent(HomeEvent.FetchSong)
                    isInitialized.value = true
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                // HomeScreen takes the full available space
                HomeScreen(
                    onEvent = albumViewModel::onEvent,
                    uiState = albumViewModel.homeUiState,
                    onClick = { navController.navigate(Destination.songScreen) },
                    modifier = Modifier.align(Alignment.TopStart)
                )

                // HomeBottomPlayer is aligned to the bottom center
                HomeBottomPlayer(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),  // Optional: Add padding if needed
                    onEvent = albumViewModel::onEvent,
                    song = musicControllerUiState.currentMusic,
                    playerState = musicControllerUiState.playerState,
                    onBarClick = { navController.navigate(Destination.songScreen) }
                )
            }
        }

        composable(route = Destination.songScreen) {
            val songViewModel: SongViewModel = hiltViewModel()
            SongScreen(
                onEvent = songViewModel::onEvent,
                song = musicControllerUiState.currentMusic,
                musicControllerUiState = musicControllerUiState,
                onNavigateUp = { navController.navigateUp() }
            )
        }

    }

}
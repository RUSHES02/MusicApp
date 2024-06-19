package com.example.musicapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.services.MediaService
import com.example.musicapp.ui.home.HomeEvent
import com.example.musicapp.ui.home.HomeScreen
import com.example.musicapp.ui.home.viewModel.AlbumViewModel
import com.example.musicapp.ui.navigation.Destination
import com.example.musicapp.ui.song.SongScreen
import com.example.musicapp.ui.song.viewModel.SongViewModel
import com.example.musicapp.ui.theme.MusicAppTheme
import com.example.musicapp.ui.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var sharedViewModel: SharedViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MusicAppTheme {
                sharedViewModel = hiltViewModel()
                MusicApp(
                    sharedViewModel = sharedViewModel,
                    modifier = Modifier
                        .fillMaxSize()
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
    NavHost(navController = navController, startDestination = Destination.home) {
        val musicControllerUiState = sharedViewModel.musicControllerUiState

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
                HomeScreen(
                    onEvent = albumViewModel::onEvent,
                    uiState = albumViewModel.homeUiState,
                    onClick = { navController.navigate(Destination.songScreen) }
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
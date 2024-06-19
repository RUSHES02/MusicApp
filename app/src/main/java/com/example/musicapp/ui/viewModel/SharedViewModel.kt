package com.example.musicapp.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.PlayerState
import com.example.musicapp.services.MusicController
import com.example.musicapp.ui.song.MusicControllerUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val musicController: MusicController) : ViewModel() {

    var musicControllerUiState by mutableStateOf(MusicControllerUiState())

    init {
        setMediaControllerCallback()
    }

    private fun setMediaControllerCallback() {

        musicController.mediaControllerCallback = { playerState, currentMusic, currentPosition, totalDuration, isShuffleEnabled, isRepeatOneEnabled ->
            println("$playerState, $currentMusic, $currentPosition, $totalDuration,$isShuffleEnabled, $isRepeatOneEnabled")
            musicControllerUiState = musicControllerUiState.copy(
                playerState = playerState,
                currentMusic = currentMusic,
                currentPosition = currentPosition,
                totalDuration = totalDuration,
            )
        }


        if (musicControllerUiState.playerState == PlayerState.PLAYING) {
            viewModelScope.launch {
                while (true) {
                    musicControllerUiState = musicControllerUiState.copy(
                        currentPosition = musicController.getCurrentPosition()
                    )
                }
            }
        }
    }

        fun destroyMediaController() {
            musicController.destroy()
        }
    }
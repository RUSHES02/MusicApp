package com.example.musicapp.ui.song

import com.example.musicapp.domain.PlayerState
import com.example.musicapp.domain.data.Song

data class MusicControllerUiState(
    val playerState: PlayerState? = null,
    val currentMusic: Song? = null,
    val currentPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val isShuffleEnabled: Boolean = false,
    val isRepeatOneEnabled: Boolean = false
)

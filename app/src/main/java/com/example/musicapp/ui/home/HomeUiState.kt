package com.example.musicapp.ui.home

import com.example.musicapp.domain.data.Song

data class HomeUiState(
    val loading: Boolean? = false,
    val songs: List<Song> = emptyList(),
    val selectedSong: Song? = null,
    val errorMessage: String? = null
)
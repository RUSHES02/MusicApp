package com.example.musicapp.ui.song

sealed class SongEvent {
    object PauseSong : SongEvent()
    object ResumeSong : SongEvent()
    object SkipToNextSong : SongEvent()
    object SkipToPreviousSong : SongEvent()
    data class SeekSongToPosition(val position: Long) : SongEvent()
}
package com.example.musicapp.ui.song.viewModel

import androidx.lifecycle.ViewModel
import com.example.musicapp.services.MusicController
import com.example.musicapp.ui.song.SongEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(private val musicController: MusicController) : ViewModel() {
    fun onEvent(event: SongEvent) {
        when (event) {
            SongEvent.PauseSong -> pauseMusic()
            SongEvent.ResumeSong -> resumeMusic()
            is SongEvent.SeekSongToPosition -> seekToPosition(event.position)
            SongEvent.SkipToNextSong -> skipToNextSong()
            SongEvent.SkipToPreviousSong -> skipToPreviousSong()
        }
    }

    private fun pauseMusic() {
        musicController.pause()
    }

    private fun resumeMusic() {
        musicController.resume()
    }

    private fun skipToNextSong() = musicController.skipToNextSong()

    private fun skipToPreviousSong() = musicController.skipToPreviousSong()

    private fun seekToPosition(position: Long) {
        musicController.seekTo(position)
    }
}

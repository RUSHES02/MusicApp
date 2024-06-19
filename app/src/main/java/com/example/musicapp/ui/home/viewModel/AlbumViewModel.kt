package com.example.musicapp.ui.home.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.Resource
import com.example.musicapp.modal.Albums
import com.example.musicapp.repository.MusicRepository
import com.example.musicapp.services.MusicController
import com.example.musicapp.ui.home.HomeEvent
import com.example.musicapp.ui.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AlbumViewModel @Inject constructor(private val musicController: MusicController, private val musicRepository: MusicRepository) : ViewModel()  {

    private val _albums = MutableStateFlow<Albums?>(null)
//    val albums: StateFlow<Albums?> get() = _albums

    var homeUiState by mutableStateOf(HomeUiState())

//    private val _errorMessage = MutableLiveData<String>()
    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.PlaySong -> playSong()

            HomeEvent.PauseSong -> pauseSong()

            HomeEvent.ResumeSong -> resumeSong()

            HomeEvent.FetchSong -> fetchAlbum()

            is HomeEvent.OnSongSelected -> homeUiState =
                homeUiState.copy(selectedSong = event.selectedSong)

            HomeEvent.SkipToNextSong -> skipToNextSong()

            is HomeEvent.SkipToPreviousSong -> skipToPreviousSong()
        }
    }


    private fun fetchAlbum() {
        viewModelScope.launch {
            Log.d("view model", "Fetching songs.....")
            musicRepository.getSongs().catch { exception ->
                homeUiState = homeUiState.copy(
                    loading = false,
                    errorMessage = exception.message
                )
                Log.d("view Model", "${exception.message}")
            }.collect { resource ->
                homeUiState = when (resource) {
                    is Resource.Success -> {
                        resource.data?.let { songs ->
                            musicController.addMediaItems(songs)
                        }
                        Log.d("view Model", " songs ${ resource.data.toString() }")

                        homeUiState.copy(
                            loading = false,
                            songs = resource.data!!
                        )
                    }

                    is Resource.Loading -> {
                        homeUiState.copy(
                            loading = true,
                            errorMessage = null
                        )
                    }

                    is Resource.Error -> {
                        homeUiState.copy(
                            loading = false,
                            errorMessage = resource.message
                        )
                    }
                }
            }
        }
    }

    private fun playSong() {
        homeUiState.apply {
            songs.indexOf(selectedSong).let { song ->
                musicController.play(song)
            }
        }
    }

    private fun pauseSong() = musicController.pause()

    private fun resumeSong() = musicController.resume()

    private fun skipToNextSong() {
        musicController.skipToNextSong()
        homeUiState = homeUiState.copy(
            selectedSong = musicController.getCurrentSong()
        )
    }

    private fun skipToPreviousSong()  {
        musicController.skipToPreviousSong()
        homeUiState = homeUiState.copy(
            selectedSong = musicController.getCurrentSong()
        )
    }

}
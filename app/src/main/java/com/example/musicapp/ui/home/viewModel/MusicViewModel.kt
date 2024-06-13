package com.example.musicapp.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.api.RetrofitInstance
import com.example.musicapp.modals.Tracks
import kotlinx.coroutines.launch

class MusicViewModel: ViewModel() {
    private val _tracks = MutableLiveData<Tracks>()
    val track: LiveData<Tracks> get() = _tracks

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun fetchSongs() {
        Log.d("view model", "Fetching songs...")
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTrack()
                Log.d("view model", "Request URL: ${response.raw().request.url}")
                Log.d("view model", "Request Headers: ${response.raw().request.headers}")
                if (response.isSuccessful && response.body() != null) {
                    _tracks.postValue(response.body())
                } else {
                    Log.e("view model", "Error: ${response.message()}")
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("view model", "Exception: ${e.message}")
                _errorMessage.postValue("Exception: ${e.message}")
            }
        }
    }
}

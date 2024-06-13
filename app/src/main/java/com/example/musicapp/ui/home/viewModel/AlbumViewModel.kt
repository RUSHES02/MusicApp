package com.example.musicapp.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.api.RetrofitInstance
import com.example.musicapp.dumm.AlbumD
import com.example.musicapp.modals.Albums
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumViewModel: ViewModel() {

    private val _album = MutableStateFlow<String?>(null)
    val album: StateFlow<String?> get() = _album

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        fetchAlbum()
    }

    fun fetchAlbum() {
        Log.d("view model", "Fetching songs...")
        viewModelScope.launch {
            try {
//                delay(50000)
                val response = RetrofitInstance.api.getAlbum()
//                Log.d("view model", "Request URL: ${response.raw().request.url}")
//                Log.d("view model", "Request Headers: ${response.raw().request.headers}")
                if (response.isSuccessful && response.body() != null) {
                    _album.value = response.body()!!
                    Log.d("view model", "Responce${response.body()}")
                } else {
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("view model", "Exception: ${e.message}")
                _errorMessage.postValue("Exception: ${e.message}")
            }
        }
    }

}
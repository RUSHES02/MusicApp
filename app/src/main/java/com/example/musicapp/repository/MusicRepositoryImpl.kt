package com.example.musicapp.repository

import com.example.musicapp.api.RetrofitInstance
import com.example.musicapp.domain.Resource
import com.example.musicapp.domain.toSong
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MusicRepositoryImpl : MusicRepository {
    override fun getSongs() = flow {
        val response = RetrofitInstance.api.getAlbumTrack()
        if (response.isSuccessful && response.body() != null) {
            val album = response.body()?.data?.album
            if (album != null && album.tracks.items.isNotEmpty()) {
                val songs = album.tracks.items.map { it.toSong() }
                emit(Resource.Success(songs))
            } else {
                emit(Resource.Error("Album or tracks data is missing"))
            }
        } else {
            emit(Resource.Error("Error fetching songs: ${response.message()}"))
        }
    }.catch { e ->
        emit(Resource.Error("Exception: ${e.message}"))
    }
}
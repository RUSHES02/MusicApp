package com.example.musicapp.repository

import com.example.musicapp.domain.Resource
import com.example.musicapp.domain.data.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getSongs(): Flow<Resource<List<Song>>>
}
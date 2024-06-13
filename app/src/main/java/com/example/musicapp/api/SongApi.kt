package com.example.musicapp.api

import com.example.musicapp.dumm.AlbumD
import com.example.musicapp.modals.Albums
import com.example.musicapp.modals.Tracks
import retrofit2.Response
import retrofit2.http.GET

interface SongApi {

    @GET("/tracks/?ids=4WNcduiCmDNfmTEz7JvmLv")
    suspend fun getTrack(): Response<Tracks>

    @GET("/album_tracks/?id=3IBcauSj5M2A6lTeffJzdv&offset=0&limit=300")
    suspend fun getAlbum(): Response<String>
}
package com.example.musicapp.api

import com.example.musicapp.data2.Albums2
import com.example.musicapp.modal.Albums
import retrofit2.Response
import retrofit2.http.GET

interface SongApi {

//    @GET("/tracks/?ids=4WNcduiCmDNfmTEz7JvmLv")
//    suspend fun getTrack(): Response<Albums.Albums.Tracks>

    @GET("/album_tracks/?id=3IBcauSj5M2A6lTeffJzdv&offset=0&limit=300")
    suspend fun getAlbumTrack(): Response<Albums?>

    @GET("albums/?ids=3IBcauSj5M2A6lTeffJzdv")
    suspend fun getAlbum(): Response<Albums2?>
}
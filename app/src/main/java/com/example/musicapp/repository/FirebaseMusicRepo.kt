package com.example.musicapp.repository

import com.example.musicapp.domain.Resource
import com.example.musicapp.domain.toSong
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class FirebaseMusicRepo: MusicRepository {
    override fun getSongs() = flow {
        val dbref = FirebaseFirestore.getInstance().collection("Songs")
        try {
            val querySnapshot = dbref.get().await()
            val songs = querySnapshot.documents.map { document ->
                document.data?.toSong(document.id) ?: throw IllegalArgumentException("Invalid song data")
            }
            emit(Resource.Success(songs))
        } catch (e: Exception) {
            emit(Resource.Error("Failed to fetch songs: ${e.message}"))
        }
    }.catch { e ->
        emit(Resource.Error("Exception: ${e.message}"))
    }
}
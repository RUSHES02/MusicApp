package com.example.musicapp.di

import android.content.Context
import com.example.musicapp.repository.FirebaseMusicRepo
import com.example.musicapp.repository.MusicRepository
import com.example.musicapp.services.MusicController
import com.example.musicapp.services.MusicControllerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Singleton
//    @Provides
//    fun provideCollection() = FirebaseFirestore.getInstance().collection(Constants.SONG_COLLECTION)

//    @Singleton
//    @Provides
//    fun provideMusicDatabase(songCollection: CollectionReference) =
//        MusicRemoteDatabase(songCollection)


    @Singleton
    @Provides
    fun provideMusicRepository(): MusicRepository = FirebaseMusicRepo()

    @Singleton
    @Provides
    fun provideMusicController(@ApplicationContext context: Context): MusicController =
        MusicControllerImpl(context)
}
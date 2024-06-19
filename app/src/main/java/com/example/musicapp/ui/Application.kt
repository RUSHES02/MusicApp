package com.example.musicapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {

//    override fun onCreate() {
//        super.onCreate()
//
//        val channel = NotificationChannel(
//            "music_play",
//            "Music App Channel",
//            NotificationManager.IMPORTANCE_DEFAULT
//        )
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//    }
}
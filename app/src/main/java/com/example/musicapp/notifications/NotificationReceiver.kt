package com.example.musicapp.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.musicapp.services.MediaService

//open class NotificationReceiver: BroadcastReceiver() {
////    override fun onReceive(context: Context?, intent: Intent?) {
////        if (context != null && intent != null) {
////            val action = intent.action
////            if (action != null) {
////                val serviceIntent = Intent(context, MediaService::class.java).apply {
////                    this.action = action
////                }
////                context.startService(serviceIntent)
////            }
////        }
////    }
//}
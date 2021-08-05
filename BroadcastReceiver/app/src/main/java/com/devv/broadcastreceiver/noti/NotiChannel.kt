package com.devv.broadcastreceiver.noti

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat

object NotiChannel {

    val MESSAGE_CHANNAL_ID = "message"
    val NEWS_CHANNAL_ID = "news"

    fun create(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createMessage(context)
            createNews(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createMessage(context: Context) {
        val name = "Message"
        val channeldescription = "Urgent messages"
        val priority = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(MESSAGE_CHANNAL_ID, name, priority).apply {
            description = channeldescription
            enableVibration(true)
            vibrationPattern = longArrayOf(100, 200, 500, 500)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null)
        }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNews(context: Context) {
        val name = "New"
        val channeldescription = "New messages"
        val priority = NotificationManager.IMPORTANCE_LOW

        val channel = NotificationChannel(NEWS_CHANNAL_ID, name, priority).apply {
            description = channeldescription
        }
        NotificationManagerCompat.from(context).createNotificationChannel(channel)
    }
}
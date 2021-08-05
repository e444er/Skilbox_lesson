package com.devv.broadcastreceiver.firebase

data class PushNotification(
    val data: NotificationData,
    val to: String,
)
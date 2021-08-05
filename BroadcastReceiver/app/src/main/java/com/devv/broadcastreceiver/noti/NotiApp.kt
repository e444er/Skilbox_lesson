package com.devv.broadcastreceiver.noti

import android.app.Application

class NotiApp:Application() {
    override fun onCreate() {
        super.onCreate()
        NotiChannel.create(this)
    }
}
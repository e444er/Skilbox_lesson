package com.devv.datatimee

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class DataAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}
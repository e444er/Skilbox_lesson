package com.devv.roomdao

import android.app.Application
import com.devv.roomdao.db.Databasee
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SkillboxApp :Application(){

 override fun onCreate() {
  super.onCreate()
  Databasee.initD(this)
 }
}
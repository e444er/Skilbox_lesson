package com.devv.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneBradcast :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAir = intent?.getBooleanExtra("state", false) ?: return
        if (isAir){
            Toast.makeText(context, "Airplane Mode true", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Airplane Mode false", Toast.LENGTH_SHORT).show()
        }
    }
}
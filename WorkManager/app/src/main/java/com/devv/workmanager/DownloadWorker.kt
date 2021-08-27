package com.devv.workmanager

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class DownloadWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val urlTo = inputData.getString(DOWNLOAD_URL_KEY)
        if (urlTo != null) {
            val requset = DownloadManager.Request(Uri.parse(urlTo))
            requset.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            requset.setTitle("Download")
            requset.setDescription("Url Download")

            requset.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            requset.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
                "${System.currentTimeMillis()}")
            requset.setAllowedOverMetered(true)

            val manager = applicationContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
            manager?.enqueue(requset)
        }
        Log.d("MYTAG", "$urlTo")
        return Result.success()
    }

    companion object {
        const val DOWNLOAD_URL_KEY = "download"
    }
}
package com.devv.scopedstoragee.test

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepository(
    private val context: Context
) {

    private val observer: ContentObserver? = null

    fun observeImages(onChange: () -> Unit){
        val observer = object : ContentObserver(Handler(Looper.getMainLooper())){
            override fun onChange(selfChange: Boolean) {
                super.onChange(selfChange)
                onChange
            }
        }
        context.contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            observer
        )
    }
    fun unregisterObserver(){
        observer?.let { context.contentResolver.unregisterContentObserver(it) }
    }

    suspend fun getImages():List<Image>{
       val images = mutableListOf<Image>()
        withContext(Dispatchers.IO){
            context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )?.use { cursor ->
                while (cursor.moveToNext()){
                    val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID))
                    val name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))

                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id)

                    images += Image(id, uri, name)

                }
            }
        }
        return images
    }

    suspend fun saveImage(name:String, url:String){
        withContext(Dispatchers.IO){
            val saveIm = saveImageDetails(name)
            downloadImage(url, saveIm)
            makeImageVisible(saveIm)
        }
    }
    private fun saveImageDetails(name:String): Uri {
        val volume = if(haveQ()){
            MediaStore.VOLUME_EXTERNAL_PRIMARY
        }else{
            MediaStore.VOLUME_EXTERNAL
        }
        val imageCollectionUri = MediaStore.Images.Media.getContentUri(volume)
        val imageDetails = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, name)
            put(MediaStore.Images.Media.MIME_TYPE, "image/*")
            if (haveQ()){
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }
        }
        return context.contentResolver.insert(imageCollectionUri, imageDetails)!!
    }

    private fun makeImageVisible(imageUri : Uri){
        if (haveQ().not()) return
        val imageDetails = ContentValues().apply {
                put(MediaStore.Images.Media.IS_PENDING, 0)
    }
        context.contentResolver.update(imageUri, imageDetails, null, null)
    }

    private suspend fun downloadImage(url: String,uri:Uri){
        context.contentResolver.openOutputStream(uri)?.use { outputSteam ->
            Network.api
                .getFile(url)
                .byteStream()
                .use { inputStream ->
                    inputStream.copyTo(outputSteam)
                }
        }
    }

    suspend fun deleteImage(id:Long){
        withContext(Dispatchers.IO){
            val uri
            = ContentUris.withAppendedId(MediaStore
                .Images.Media.EXTERNAL_CONTENT_URI,id)
            context.contentResolver.delete(uri, null, null)
        }
    }
}
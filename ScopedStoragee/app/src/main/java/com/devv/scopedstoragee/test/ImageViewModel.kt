package com.devv.scopedstoragee.test

import android.app.Application
import android.app.RecoverableSecurityException
import android.app.RemoteAction
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ImageViewModel(app:Application):AndroidViewModel(app) {

    private val _permissionsGLData = MutableLiveData<Boolean>()

    val permissionsGLData: LiveData<Boolean>
        get() = _permissionsGLData

    private val _imagesMutableLiveData = MutableLiveData<List<Image>>()

    val imagesLiveData: LiveData<List<Image>>
        get() = _imagesMutableLiveData

    private val _recMutableLiveData = MutableLiveData<RemoteAction>()

    val recMutableLiveData: LiveData<RemoteAction>
        get() = _recMutableLiveData

    private var isObser : Boolean = false
    private var pendingDeleteId:Long? = null

    private val repo = ImageRepository(app)

    fun saveImages(name: String, url:String) {
        try {
            viewModelScope.launch {
               repo.saveImage(name, url)
            }
        } catch (t: Throwable) {
            Log.d("ERRORR", "${saveImages(name, url)}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        repo.unregisterObserver()
    }

    fun updatePermissionState(isGranted:Boolean) {
        if (isGranted){
            permissionGranted()
        }else{
            permissionDenied()
        }
    }

     fun permissionDenied() {
        loadImages()
         if (isObser.not()){
             repo.observeImages { loadImages() }
             isObser = true
         }
        _permissionsGLData.postValue(true)
    }

    private fun loadImages() {
        viewModelScope.launch {
            try {
                val image = repo.getImages()
                _imagesMutableLiveData.postValue(image)
            }catch (t:Throwable){
                _imagesMutableLiveData.postValue(emptyList())
            }
        }
    }

    fun permissionGranted() {
        _permissionsGLData.postValue(false)
    }
    fun deleteImage(id:Long) {
        viewModelScope.launch {
            try {
                repo.deleteImage(id)
                pendingDeleteId = null
            }catch (t:Throwable){
                if (haveQ() && t is RecoverableSecurityException){
                    pendingDeleteId = id
                    _recMutableLiveData.postValue(t.userAction)
                }else{
                    Log.d("tag", "delete")
                }
            }
        }
    }
    fun configDeleteId(){
        pendingDeleteId?.let {
            deleteImage(it)
        }
    }
    fun declineDelete(){
        pendingDeleteId = null
    }
}
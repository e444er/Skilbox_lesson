package com.devv.contentprovider

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AddViewmodel(app: Application) : AndroidViewModel(app) {

    private val repository = ContactRepository(app)

    private val saveSuccessLive = MutableLiveData<Unit>()

    val saveSuccess: LiveData<Unit>
        get() = saveSuccessLive

    fun save(name: String, number: String) {
        viewModelScope.launch {
            try {
                repository.saveContact(name, number)
                saveSuccessLive.postValue(Unit)
            }catch (t:Throwable){
                error(t)
            }
        }
    }
}
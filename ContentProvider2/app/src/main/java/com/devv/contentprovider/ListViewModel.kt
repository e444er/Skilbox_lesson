package com.devv.contentprovider
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = ContactRepository(application)

    private val callMutableLiveData = MutableLiveData<String>()
    private val contactMutableLiveData = MutableLiveData<List<Contact>>()

    val callLiveData: LiveData<String>
        get() = callMutableLiveData

    val contactLiveData: LiveData<List<Contact>>
        get() = contactMutableLiveData

    fun loadList() {
        viewModelScope.launch {
            try {
                contactMutableLiveData.postValue(repo.getAllContacts())
            } catch (t: Throwable) {
                contactMutableLiveData.postValue(emptyList())
            }
        }
    }

    fun callToContact(contact: Contact) {
        contact.number.firstOrNull()?.let { callMutableLiveData.postValue(it) }
    }

}
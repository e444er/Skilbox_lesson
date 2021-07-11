package com.devv.navviewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonListViewModel : ViewModel() {

    private val personPepository = PersonPepository()

    private val personLiveData = MutableLiveData(personPepository.persons)

    private val showToastLiveData = MutableLiveData<Unit>()

    val persons: LiveData<List<Person>>
        get() = personLiveData

    val showToast: LiveData<Unit>
        get() = showToastLiveData

    fun addPerson() {
        val newUser = personPepository.createPerson()
        val updateList = listOf(newUser) + personLiveData.value.orEmpty()
        personLiveData.postValue(updateList)
        showToastLiveData.postValue(Unit)
    }

    fun deletePerson(position: Int) {
        personLiveData.postValue(
            personPepository.deletePerson(
                personLiveData.value.orEmpty(),
                position
            )
        )
    }
}
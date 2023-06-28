package com.example.contactmanagerapp.viewModel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactmanagerapp.room.User
import com.example.contactmanagerapp.room.UserRepository
import kotlinx.coroutines.launch

/**
 * @author Vijila P
 */
class UserViewModel(private val repository: UserRepository):ViewModel(),Observable {
    val users = repository.users
    private var isUpdateOrDelete = false
    private lateinit var userToUpdateOrDelete : User

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputEmail = MutableLiveData<String?>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate(){
        if(isUpdateOrDelete){
            //update

            userToUpdateOrDelete.name = inputName.value!!
            userToUpdateOrDelete.email = inputEmail.value!!

            update(userToUpdateOrDelete)
        }
        else{

            //insert
            val name = inputName.value!!
            val email = inputEmail.value!!

            insert(User(0,name,email))

            inputName.value = null
            inputEmail.value = null
        }

    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(userToUpdateOrDelete)
        }
        else{
            clearAll()
        }
    }

    fun insert(user: User)= viewModelScope.launch {
        repository.insertUser(user)
    }

    fun clearAll()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun update(user: User)= viewModelScope.launch {
        repository.updateUser(user)

//reset
        inputEmail.value = null
        inputName.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "ClearAll"

    }

    fun delete(user: User)= viewModelScope.launch {

        repository.deleteUser(user)
        inputEmail.value = null
        inputName.value = null
        isUpdateOrDelete = false
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "ClearAll"
    }


    fun initUpdateAndDelete(user: User){
        inputEmail.value = user.email
        inputName.value = user.name
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}
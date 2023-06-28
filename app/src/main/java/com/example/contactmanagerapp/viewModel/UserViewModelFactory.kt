package com.example.contactmanagerapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contactmanagerapp.room.UserRepository
import java.lang.IllegalArgumentException

/**
 * @author Vijila P
 */
class UserViewModelFactory(private val repository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Illegal Argument Exception")
        return super.create(modelClass)
    }
}
package com.uc3m.searchyourrecipe.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.uc3m.searchyourrecipe.database.UserDatabase
import com.uc3m.searchyourrecipe.models.User
import com.uc3m.searchyourrecipe.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val repository: UserRepository
    val readAll: LiveData<List<User>>

    init{
        val userDAO = UserDatabase.getDatabase(application).userDAO()
        repository = UserRepository(userDAO)
        readAll = repository.readAll
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }
}
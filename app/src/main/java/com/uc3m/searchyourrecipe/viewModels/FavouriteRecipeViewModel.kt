package com.uc3m.searchyourrecipe.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.uc3m.searchyourrecipe.models.FavouriteRecipe
import com.uc3m.searchyourrecipe.models.FavouriteRecipeDatabase
import com.uc3m.searchyourrecipe.models.FavouriteRecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteRecipeViewModel(application: Application): AndroidViewModel(application) {

    val readAll: LiveData<List<FavouriteRecipe>>
    private val repository: FavouriteRecipeRepository

    init {
        val favRecipeDAO = FavouriteRecipeDatabase.getDatabase(application).favRecipeDAO()
        repository = FavouriteRecipeRepository(favRecipeDAO)
        readAll = repository.readAll
    }

    fun addFavRecipe(favRecipe: FavouriteRecipe){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavRecipe(favRecipe)
        }
    }

}
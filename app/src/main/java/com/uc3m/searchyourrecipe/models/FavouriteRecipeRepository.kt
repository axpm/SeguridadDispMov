package com.uc3m.searchyourrecipe.models

import androidx.lifecycle.LiveData

class FavouriteRecipeRepository(private val favRecipeDAO: FavouriteRecipeDAO) {

    val readAll: LiveData<List<FavouriteRecipe>> = favRecipeDAO.readAll()

    suspend fun addFavRecipe(favRecipe: FavouriteRecipe){
        favRecipeDAO.addFavRecipe(favRecipe)
    }
}
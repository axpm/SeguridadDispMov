package com.uc3m.searchyourrecipe.models

import androidx.lifecycle.LiveData

class FavoriteRecipeRepository(private val favRecipeDAO: FavoriteRecipeDAO) {

    val readAll: LiveData<List<FavoriteRecipe>> = favRecipeDAO.readAll()

    suspend fun addFavRecipe(favRecipe: FavoriteRecipe){
        favRecipeDAO.addFavRecipe(favRecipe)
    }
}
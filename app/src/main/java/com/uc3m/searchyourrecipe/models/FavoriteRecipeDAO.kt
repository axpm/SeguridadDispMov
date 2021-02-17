package com.uc3m.searchyourrecipe.models

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface FavoriteRecipeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavRecipe(facRecipe: FavoriteRecipe)

    @Query("SELECT * FROM favorite_recipe_table ORDER BY title DESC")
    fun readAll(): LiveData<List<FavoriteRecipe>>
}
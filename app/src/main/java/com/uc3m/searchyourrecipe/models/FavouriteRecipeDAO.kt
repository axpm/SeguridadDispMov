package com.uc3m.searchyourrecipe.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavouriteRecipeDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavRecipe(favRecipe: FavouriteRecipe)

    @Query("SELECT * FROM favorite_recipe_table ORDER BY title DESC")
    fun readAll(): LiveData<List<FavouriteRecipe>>

    @Delete
    fun removeFavRecipe(favRecipe: FavouriteRecipe)
}
package com.uc3m.searchyourrecipe.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipe_table")
data class FavouriteRecipe(
    @PrimaryKey(autoGenerate = false)
    val id: Int, // uri
    val title: String, // label
    val calories: Int, // calories
    val img: String //link a la imagen
)
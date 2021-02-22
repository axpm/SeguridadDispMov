package com.uc3m.searchyourrecipe.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Food(
    val foodId: String, // foodId
    val label: String
)

data class Measure(
    val uri: String,
    val label: String
)

data class Ingredient (
    val foodId: String, // foodId
    val quantity: String, //quantity
    val measure: Measure,
    val food: Food
)

data class Recipe(
    @SerializedName("uri")
    val id: Int, // uri
    val title: String, // label
    val calories: Int, // calories
    val url: String, // url
    @SerializedName("image")
    val img: String, //image --> link a la imagen
    val ingredients: List<Ingredient>,
    @SerializedName("totalTime")
    val time: Int, //totalTime --> tiempo en minutos
)




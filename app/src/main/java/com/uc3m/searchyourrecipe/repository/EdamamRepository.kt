package com.uc3m.searchyourrecipe.repository

import com.uc3m.searchyourrecipe.api.RetroFitInstance
import com.uc3m.searchyourrecipe.models.EdamamRecipe
import com.uc3m.searchyourrecipe.models.Recipe
import retrofit2.Response

class EdamamRepository {

    suspend fun searchRecipe(query: String): Response<EdamamRecipe>{
        return RetroFitInstance.edamamAPI.searchRecipe(query)
    }

    suspend fun getRecipe(uri: String): Response<List<Recipe>>{
        return RetroFitInstance.edamamAPI.getRecipe(uri)
    }

}
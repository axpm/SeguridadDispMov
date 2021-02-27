package com.uc3m.searchyourrecipe.api

import com.uc3m.searchyourrecipe.models.EdamamRecipe
import com.uc3m.searchyourrecipe.models.Recipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamAPI {

    @GET("/search")
    suspend fun searchRecipe(@Query("q") recipe: String,
                             @Query("from") from: Int = 0,
                             @Query("to") to: Int = 5,
                             @Query("app_id") appID: String = "148f7723",
                             @Query("app_key") appKey: String = "c88f00303803a2f18c7135babee0b61a"
    ): Response<EdamamRecipe>;

    @GET("/search")
    suspend fun getRecipe(@Query("r") id: String,
                          @Query("app_id") appID: String = "148f7723",
                          @Query("app_key") appKey: String = "c88f00303803a2f18c7135babee0b61a"
    ): Response<List<Recipe>>;

}
package com.uc3m.searchyourrecipe.api

import com.uc3m.searchyourrecipe.util.Constants.Companion.EDAMAN_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {
    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(EDAMAN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /*val EdamanAPI: EdamanAPI by lazy{
        retrofit.create(EdamanAPI::class.java)
    }*/
}
package com.uc3m.searchyourrecipe.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteRecipe::class], version = 1, exportSchema = false)
abstract class FavoriteRecipeDatabase: RoomDatabase() {

    abstract fun favRecipeDAO(): FavoriteRecipeDAO

    companion object{
        @Volatile
        private var INSTANCE: FavoriteRecipeDatabase? = null

        fun getDatabase(context: Context): FavoriteRecipeDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteRecipeDatabase::class.java,
                        "favorite_recipe_database"
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }
        }
    }
}
package com.uc3m.searchyourrecipe.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoppingListItem::class], version = 1, exportSchema = false)
abstract class ShoppingListItemDatabase: RoomDatabase() {

    abstract fun shoppingListItemDAO(): ShoppingListItemDAO

    companion object{
        @Volatile
        private var INSTANCE: ShoppingListItemDatabase? = null
        //Creo una instancia de la bbdd. Si existe, devuelve la instancia (conexion a la bbdd)
// y si no existe, la crea con el Room.databaseBuilder especificando el contexto, el tipo de entidad (ShoppingListItem) y el nombre de la bbdd
        fun getDatabase(context: Context): ShoppingListItemDatabase{
            synchronized(this){ //no hay que poner lock, solo this y sale solo //sincro: cuando tienes concurrencia, te limita que solo se va a ejecutar una vez
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ShoppingListItemDatabase::class.java,
                            "shoppingListItem_database" //no hay que poner name, sale solo
                    ).fallbackToDestructiveMigration().build()
                }
                return instance
            }

        }
    }
}
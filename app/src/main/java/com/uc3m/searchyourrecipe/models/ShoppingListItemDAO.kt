package com.uc3m.searchyourrecipe.models

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingListItemDAO {
        //Añadir un ingrediente a la shoppinglist
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addIngredient(ingredient: ShoppingListItem)

        //Visualizar los ingredientes de la shoppinglist
        @Query("SELECT * FROM shopping_list_item_table ORDER BY nameIngredient ASC")
        fun readAll(): LiveData<List<ShoppingListItem>>

        //Eliminar un ingrediente a la shoppinglist
        @Delete
         fun deleteIngredient(ingredient: ShoppingListItem)

}
package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.searchyourrecipe.databinding.RecyclerViewIngredientItemBinding
import com.uc3m.searchyourrecipe.models.ShoppingListItem
import com.uc3m.searchyourrecipe.models.ShoppingListItemDAO
import com.uc3m.searchyourrecipe.models.ShoppingListItemRepository
import com.uc3m.searchyourrecipe.viewModels.ShoppingListItemViewModel

class ShoppingListAdapter : RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder>() {

    private var shoppingList = emptyList<ShoppingListItem>()
    private lateinit var shoppingListDAO: ShoppingListItemDAO
    private lateinit var shoppingListItemViewModel:ShoppingListItemViewModel
    class MyViewHolder(val binding: RecyclerViewIngredientItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewIngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent,
                false)
       // shoppingListItemViewModel = ViewModelProvider(this).get(ShoppingListItemViewModel::class.java)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = shoppingList[position]
        with(holder){
            binding.ingredient.text = currentItem.nameIngredient.toString()
            binding.deleteIngredient.setOnClickListener{
                deleteIngre(position) //El metodo se encuentra abajo
                println("funciona?")
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    fun setData(shoppingList: List<ShoppingListItem>){
        this.shoppingList = shoppingList
        //notificar cuando hay cambios y todos los que esten observando este liveData se actualizaran
        notifyDataSetChanged()
    }
    fun deleteIngre(position: Int) {
        val item = shoppingList[position]
        (shoppingList as MutableList).remove(item)
        //Cuando llamo al metodo, me indica que shoppingListItemViewModel no esta inicializado
        shoppingListItemViewModel.deleteIngredient(item)
        notifyItemChanged(position)
    }


}
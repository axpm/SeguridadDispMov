package com.uc3m.searchyourrecipe.views

import android.R
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uc3m.searchyourrecipe.databinding.RecyclerViewRecipeItemBinding
import com.uc3m.searchyourrecipe.models.FavouriteRecipe


class FavRecipesAdapter: RecyclerView.Adapter<FavRecipesAdapter.MyViewHolder>() {

    private var favRecipesList = emptyList<FavouriteRecipe>()

    class MyViewHolder(val binding: RecyclerViewRecipeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewRecipeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = favRecipesList[position]
        with(holder){
            binding.recipeName.text = currentItem.title.toString()
            binding.calories.text = currentItem.calories.toString()
            binding.imageRecipe.setImageURI(Uri.parse(currentItem.img.toString()))
        }
    }

    override fun getItemCount(): Int {
        return favRecipesList.size
    }

    fun setData(studentList: List<FavouriteRecipe>){
        this.favRecipesList = studentList
        notifyDataSetChanged()
    }

}
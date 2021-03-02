package com.uc3m.searchyourrecipe.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
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
            binding.time.text = currentItem.time.toString() + "min"
            Picasso.get().load(currentItem.img.toString()).into(binding.imageRecipe)

            //Si pinchamos sobre la imagen o el nombre, accedemos a la informacion de la receta
            binding.imageRecipe.setOnClickListener {
                val action = FavRecipesFragmentDirections
                    .actionFavRecipesFragmentToRecipeFragment(currentItem.id.toString(), "", null)
                holder.itemView.findNavController().navigate(action)
            }

            binding.recipeName.setOnClickListener {
                val action = FavRecipesFragmentDirections
                    .actionFavRecipesFragmentToRecipeFragment(currentItem.id.toString(), "", null)
                holder.itemView.findNavController().navigate(action)
            }
            //Añadimos a favoritos
            binding.starButton.setOnClickListener {

            }

        }
    }

    override fun getItemCount(): Int {
        return favRecipesList.size
    }

    fun setData(list: List<FavouriteRecipe>){
        this.favRecipesList = list
        notifyDataSetChanged()
    }

}
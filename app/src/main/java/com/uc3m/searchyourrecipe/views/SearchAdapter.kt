package com.uc3m.searchyourrecipe.views

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uc3m.searchyourrecipe.databinding.RecyclerViewRecipeItemBinding
import com.uc3m.searchyourrecipe.models.FavouriteRecipe
import com.uc3m.searchyourrecipe.models.Hit
import com.uc3m.searchyourrecipe.models.Recipe
import com.uc3m.searchyourrecipe.viewModels.FavouriteRecipeViewModel

class SearchAdapter(private val favViewModel: FavouriteRecipeViewModel): RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    private var recipesList = emptyList<Hit>()

    class MyViewHolder(val binding: RecyclerViewRecipeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerViewRecipeItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recipesList[position].recipe
        with(holder){
            binding.recipeName.text = currentItem.title
            binding.time.text = currentItem.time.toString() + "min"

            Picasso.get().load(currentItem.img).into(binding.imageRecipe)

            binding.imageRecipe.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(currentItem.id, "search", currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            binding.recipeName.setOnClickListener {
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(currentItem.id, "search", currentItem)
                holder.itemView.findNavController().navigate(action)
            }

            /*
            // comprobar el id para cambiar
            if ( isInFav(currentItem.id) ){
                // poner estrella rellena
                binding.starButton.setImageResource(R.drawable.ic_baseline_star_24)
                // eliminar de favoritos y cambiar el icono
                binding.starButton.setOnClickListener{
                    removeFavRecipe(currentItem.id)
                    binding.starButton.setImageResource(R.drawable.ic_baseline_star_border_24)
                }
            }else{
                // poner estrella sin relleno
                binding.starButton.setImageResource(R.drawable.ic_baseline_star_border_24)
                // añadir a favoritos y cambiar el icono
                binding.starButton.setOnClickListener{
                    val newFav = FavouriteRecipe(currentItem.id, currentItem.title, currentItem.img, currentItem.time)
                    addFavRecipe(newFav)
                    binding.starButton.setImageResource(R.drawable.ic_baseline_star_24)
                }
            }
            */


        }
    }

    private fun addFavRecipe(newFav: FavouriteRecipe) {
        favViewModel.addFavRecipe(newFav)
    }

    private fun removeFavRecipe(id: String) {
        favViewModel.deleteFavRecipe(id)
        //notifyItemChanged(position)
    }

    private fun isInFav(id: String): Boolean {
        var ret: Boolean = false

        //favViewModel.existsFavRecipeById(id).observe(this, ret)
        //favViewModel.existsFavRecipeById(id).observe(viewLifecycleOwner, {
        //    ret -> ret = val
        //})
        return false
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    fun setData(list: List<Hit>){
        this.recipesList = list
        notifyDataSetChanged()
    }

}
package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentFavRecipesBinding
import com.uc3m.searchyourrecipe.viewModels.FavouriteRecipeViewModel

class FavRecipesFragment : Fragment() {
    private lateinit var  binding:  FragmentFavRecipesBinding
    private lateinit var favRecipesViewModel: FavouriteRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavRecipesBinding.inflate(inflater, container, false)
        val view = binding.root

        val adapter = FavRecipesAdapter()
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        favRecipesViewModel = ViewModelProvider(this).get(FavouriteRecipeViewModel::class.java)
        favRecipesViewModel.readAll.observe(viewLifecycleOwner, {
            favRecipe -> adapter.setData(favRecipe)
        })

        return view
    }
}
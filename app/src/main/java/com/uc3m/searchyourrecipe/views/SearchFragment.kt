package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentSearchBinding
import com.uc3m.searchyourrecipe.repository.EdamamRepository
import com.uc3m.searchyourrecipe.viewModels.FavouriteRecipeViewModel
import com.uc3m.searchyourrecipe.viewModels.SearchViewModel
import com.uc3m.searchyourrecipe.viewModels.SearchViewModelFactory

class SearchFragment : Fragment() {

    //private lateinit var searchViewModel: SearchViewModel
    private lateinit var  binding: FragmentSearchBinding
    private lateinit var favRecipesViewModel: FavouriteRecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root

        // FAV RECIPES
        favRecipesViewModel = ViewModelProvider(this).get(FavouriteRecipeViewModel::class.java)

        // SEARCH
        val edamamRepository = EdamamRepository()

        val searchViewModelFactory = SearchViewModelFactory(edamamRepository)
        val searchViewModel = ViewModelProvider(this, searchViewModelFactory).get(SearchViewModel::class.java)
        searchViewModel.searchRecipe("chicken")

        val adapter = SearchAdapter(favRecipesViewModel)
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchViewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful){
                /*
                val recipeTitle = response.body()?.toString()

                val displayText = "Price: {recipeTitle.toString()} €"
                Log.d("Response", recipeTitle.toString())
                */

                val hits = response.body()?.hits

                if (hits != null) {
                    adapter.setData(hits)
                    /*
                    for (hit in hits){
                        adapter.setData(hit.recipe)
                    }*/
                }

            }else{
                Log.d("Response", response.errorBody().toString())

                Toast.makeText(requireContext(),"Something went wrong",
                        Toast.LENGTH_SHORT).show()


            }
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }


}
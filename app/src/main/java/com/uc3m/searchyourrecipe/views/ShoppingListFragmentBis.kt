package com.uc3m.searchyourrecipe.views


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.DialogAddIngredientBinding
import com.uc3m.searchyourrecipe.databinding.FragmentShoppingListBinding
import com.uc3m.searchyourrecipe.databinding.RecyclerViewIngredientItemBinding
import com.uc3m.searchyourrecipe.models.ShoppingListItem
import com.uc3m.searchyourrecipe.viewModels.ShoppingListItemViewModel


class ShoppingListFragmentBis : Fragment() {
    private lateinit var binding: FragmentShoppingListBinding
    private lateinit var bindingRecyclerView: RecyclerViewIngredientItemBinding

    private lateinit var shoppingListItemViewModel: ShoppingListItemViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        bindingRecyclerView = RecyclerViewIngredientItemBinding.inflate(inflater, container, false)

        val view = binding.root

        //Lista de ingredientes
        val adapter = ShoppingListAdapter()
        val recyclerView = binding.recyclerViewShoppingList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        shoppingListItemViewModel = ViewModelProvider(this).get(ShoppingListItemViewModel::class.java)
        shoppingListItemViewModel.readAll.observe(viewLifecycleOwner, { shoppingListItem ->
            adapter.setData(shoppingListItem)
        })

        //Boton ADD
        binding.addIngredient.setOnClickListener{
            findNavController().navigate(R.id.action_shoppingListFragmentBis_to_new_ingredient)
        }

        return view
    }


}
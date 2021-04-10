package com.uc3m.searchyourrecipe.views

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentNewIngredientBinding
import com.uc3m.searchyourrecipe.models.ShoppingListItem
import com.uc3m.searchyourrecipe.viewModels.ShoppingListItemViewModel


class NewIngredientFragment : Fragment() {

    private  lateinit var binding: FragmentNewIngredientBinding
    private lateinit var shoppingListItemViewModel: ShoppingListItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideKeyboard()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentNewIngredientBinding.inflate(inflater, container, false)

        shoppingListItemViewModel = ViewModelProvider(this).get(ShoppingListItemViewModel::class.java)

        //Boton de añadir
        binding.entrarBoton.setOnClickListener{
            insertDataToDatabase()
            (activity as MainActivity).hideBottomNavigation()
        }

        return binding.root
    }
    //Comunicarse con Room
    private fun insertDataToDatabase(){
        //Cogemos los datos de la interfaz
        val ingredientName = binding.nombreInput.text.toString()

        //validamos que no este vacio
        if (inputCheck(ingredientName)){
            val ingredient = ShoppingListItem(ingredientName)
            shoppingListItemViewModel.addIngredient(ingredient)
            Toast.makeText(requireContext(), "Ingredient Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newIngredient_to_shoppingListFragmentBis)

        }else{
            Toast.makeText(requireContext(), "Fill the field", Toast.LENGTH_LONG).show()
        }

    }

    //Validar que no esten vacios
    private fun inputCheck(ingredientName: String): Boolean{
        return !(TextUtils.isEmpty(ingredientName))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()
    }
}
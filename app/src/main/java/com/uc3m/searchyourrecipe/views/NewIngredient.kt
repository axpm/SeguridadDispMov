package com.uc3m.searchyourrecipe.views

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


class NewIngredient : Fragment() {

    private  lateinit var binding: FragmentNewIngredientBinding
    private lateinit var shoppingListItemViewModel: ShoppingListItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        println("holaa")
        binding = FragmentNewIngredientBinding.inflate(inflater, container, false)

        shoppingListItemViewModel = ViewModelProvider(this).get(ShoppingListItemViewModel::class.java)
        //Boton de anyadir contacto

        binding.entrarBoton.setOnClickListener{

            insertDataToDatabase()
        }
        return binding.root
    }
    //Comunicarse con Room
    private fun insertDataToDatabase(){
        //cogemos los bdatos de la interfaz
        val ingredientName = binding.nombreInput.text.toString()

        println("ingredinetee " +ingredientName)
        //validamos
        if (inputCheck(ingredientName)){
            //En el id ponemos 0, pero nos da igual porque es autoincremental. Convertimos age de editable a string y, luego a entero
            val ingredient = ShoppingListItem(ingredientName)
            shoppingListItemViewModel.addIngredient(ingredient)
            Toast.makeText(requireContext(), "Ingredient Added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newIngredient_to_shoppingListFragmentBis)

        }else{
            Toast.makeText(requireContext(), "Fill all the fields", Toast.LENGTH_LONG).show()
        }

    }
    //Validar que no esten vacios
    private fun inputCheck(ingredientName: String): Boolean{
        return !(TextUtils.isEmpty(ingredientName))

    }
}
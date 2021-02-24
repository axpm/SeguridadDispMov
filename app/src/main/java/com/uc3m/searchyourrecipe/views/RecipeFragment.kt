package com.uc3m.searchyourrecipe.views

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.uc3m.searchyourrecipe.databinding.FragmentRecipeBinding


class RecipeFragment : Fragment() {

    val args: RecipeFragmentArgs by navArgs()
    private lateinit var  binding: FragmentRecipeBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        // recogemos el parámetro "id"
        val idArgument = args.id
        // cambiar el título por el ID
        val title = binding.title
        title.text = idArgument

        return view
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
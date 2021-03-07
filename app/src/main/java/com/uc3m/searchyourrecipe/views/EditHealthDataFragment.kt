package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentEditHealthDataBinding

class EditHealthDataFragment : Fragment() {

    private lateinit var binding: FragmentEditHealthDataBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditHealthDataBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}
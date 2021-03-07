package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uc3m.searchyourrecipe.databinding.FragmentHealthDataBinding


class HealthDataFragment : Fragment() {

    private lateinit var binding: FragmentHealthDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthDataBinding.inflate(inflater, container, false)
        val view = binding.root
        

        return view
    }

}
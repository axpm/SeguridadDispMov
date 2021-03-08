package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentEditHealthDataBinding
import java.io.File
import java.nio.charset.StandardCharsets

class EditHealthDataFragment : Fragment() {

    private lateinit var binding: FragmentEditHealthDataBinding

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditHealthDataBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.saveButton.setOnClickListener {
            val age = binding.ageInput.text
            val height = binding.heightInput.text
            val weight = binding.weightInput.text

            if(checkValues(age, height, weight)){
                saveHealthData(age, height, weight)
                findNavController().navigate(R.id.action_editHealthDataFragment_to_healthDataFragment)
            }
        }


        return view
    }


    private fun checkValues(age: Editable?, height: Editable?, weight: Editable?): Boolean {
        return true
    }

    private fun saveHealthData(age: Editable?, height: Editable?, weight: Editable?) {
        val mainKey = MasterKey.Builder(requireContext())
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        // Create a file with this name, or replace an entire existing file
        // that has the same name. Note that you cannot append to an existing file,
        // and the file name cannot contain path separators.
        val fileName = "health_data.txt"
        val dir = "/data/data/com.uc3m.searchyourrecipe"
        val file = File(dir, fileName)
        if (file.exists()){
            file.delete()
        }
        val encryptedFile = EncryptedFile.Builder(
            requireContext(),
            file,
            mainKey,
            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
        ).build()
        val fileContent = ("age:"+ age.toString()+ "||" + "height:" + height.toString() + "||" + "weight:" + weight.toString())
            .toByteArray(StandardCharsets.UTF_8)

        encryptedFile.openFileOutput().apply {
            write(fileContent)
            flush()
            close()
        }
    }

}
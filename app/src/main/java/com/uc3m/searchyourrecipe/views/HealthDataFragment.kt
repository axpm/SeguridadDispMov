package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentHealthDataBinding
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.log
import kotlin.math.sqrt


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

        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_healthDataFragment_to_editHealthDataFragment)
        }

        readHealthData()


        return view
    }

    private fun readHealthData() {
        val mainKey = MasterKey.Builder(requireContext())
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        val fileToRead = "health_data.txt"
        val DIRECTORY = "/data/data/com.uc3m.searchyourrecipe"
        if (File(DIRECTORY, fileToRead).exists()){
            val encryptedFile = EncryptedFile.Builder(
                    requireContext(),
                    File(DIRECTORY, fileToRead),
                    mainKey,
                    EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
            ).build()
            val inputStream = encryptedFile.openFileInput()
            val byteArrayOutputStream = ByteArrayOutputStream()
            var nextByte: Int = inputStream.read()
            while (nextByte != -1) {
                byteArrayOutputStream.write(nextByte)
                nextByte = inputStream.read()
            }
            val plaintext: ByteArray = byteArrayOutputStream.toByteArray()
            val plainString = String(plaintext)

            if (plainString != ""){
                val strs = plainString.split("||").toTypedArray()
                val height = strs[1].split(":").toTypedArray()[1]
                val weight = strs[2].split(":").toTypedArray()[1]
                binding.ageText.text = strs[0].split(":").toTypedArray()[1].toString()
                binding.heightText.text = height.toString()
                binding.weightText.text = weight.toString()
                binding.imcText.text = calculateIMC(height, weight)
            }
        }


    }

    private fun calculateIMC(height: String, weight: String): CharSequence? {
        return (weight.toFloat() / (height.toFloat() * height.toFloat())).toString()
    }

}
package com.uc3m.searchyourrecipe.views

import android.annotation.SuppressLint
import android.hardware.biometrics.BiometricManager
import androidx.biometric.BiometricPrompt;
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager.from
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentUserBinding
import com.uc3m.searchyourrecipe.viewModels.UserViewModel
import java.util.concurrent.Executor

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideKeyboard()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.privateButton.setOnClickListener {
            configureBiometric()
            biometricPrompt.authenticate(promptInfo)
        }

        showDataUser(binding)

        return view
    }

    private fun showDataUser(binding: FragmentUserBinding) {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.readAll.observe(viewLifecycleOwner, { list ->
            run{
                val currentItem = list.get(0)
                binding.userName.text = currentItem.name

                Picasso.get().load(currentItem.image).into(binding.imageUser)
            }
        })
    }

    private fun configureBiometric() {
        // se usa setDeviceCredentialAllowed aunque está deprecated porque es lo que funciona en
        // Android 10 y 11 y podemos probarlo en el móvil físico y virtual
        promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("We care about your privacy")
                .setDeviceCredentialAllowed(true)
                .build()

        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        Toast.makeText(requireContext(),
                                "Authentication error: $errString", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onAuthenticationSucceeded(
                            result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        Toast.makeText(requireContext(),
                                "Authentication succeeded!", Toast.LENGTH_SHORT)
                                .show()

                        findNavController().navigate(R.id.action_userFragment_to_healthDataFragment)

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        Toast.makeText(requireContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                                .show()
                    }
                })

    }

}
package com.uc3m.searchyourrecipe.views

import android.annotation.SuppressLint
import androidx.biometric.BiometricPrompt;
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.uc3m.searchyourrecipe.R
import com.uc3m.searchyourrecipe.databinding.FragmentUserBinding
import java.util.concurrent.Executor

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as MainActivity).hideKeyboard()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.privateButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        configureBiometric()

        return view
    }

    private fun configureBiometric() {
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

        // se usa setDeviceCredentialAllowed aunque está deprecated porque es lo que funciona en
        // Android 10 y 11 y podemos probarlo en el móvil físico y virtual
        promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("We care about your privacy")
                .setDeviceCredentialAllowed(true)
                .build()
    }

}
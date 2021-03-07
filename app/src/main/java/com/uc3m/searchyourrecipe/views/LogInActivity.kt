package com.uc3m.searchyourrecipe.views


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uc3m.searchyourrecipe.databinding.ActivityLogInBinding


class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_log_in)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpButton(binding)

    }

    private fun setUpButton(binding: ActivityLogInBinding) {
        binding.button.setOnClickListener {
            // Hacer lo del login
            // por ahora simplemente navega hacia el MainActivity
            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }
    }

}
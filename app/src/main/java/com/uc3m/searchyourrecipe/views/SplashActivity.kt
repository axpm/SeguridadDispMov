package com.uc3m.searchyourrecipe.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.uc3m.searchyourrecipe.R

class SplashActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 1500 // 1.5 sec

    private val TEST: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Cambiar por comprobación en la BD, un observer
        if(TEST){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))

                // close this activity
                finish()
            }, SPLASH_TIME_OUT)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LogInActivity::class.java))

                // close this activity
                finish()
            }, SPLASH_TIME_OUT)
        }

        // hasta aquí dentro del observer

    }
}
package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uc3m.searchyourrecipe.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun setupViews(){
        // Coger el BottomNavigationView
        bottomNavView = findViewById(R.id.bottomNavView)

        // Coger el Navigation Controller
        var navController = findNavController(R.id.fragNavHost)

        // Asociar el la Navigation Controller con el BottomNavigationView
        bottomNavView.setupWithNavController(navController)

        // Setting Up ActionBar with Navigation Controller
        // Pass the IDs of top-level destinations in AppBarConfiguration
        var appBarConfiguration = AppBarConfiguration(
                topLevelDestinationIds = setOf (
                        R.id.favRecipesFragment,
                        R.id.searchFragment,
                        R.id.shoppingListFragment,
                        R.id.userFragment
                )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}
package com.uc3m.searchyourrecipe.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uc3m.searchyourrecipe.R

//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //lateinit var navController: NavController
    lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
    }

    private fun setupViews(){

        bottomNavView = findViewById(R.id.bottomNavView)

        /*
        val navController = Navigation.findNavController(this, R.id.fragNavHost)
        bottomNavView.setupWithNavController(navController)
        */


        // Finding the Navigation Controller
        var navController = findNavController(R.id.fragNavHost)

        // Setting Navigation Controller with the BottomNavigationView
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

        // Setting Up ActionBar with Navigation Controller
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}
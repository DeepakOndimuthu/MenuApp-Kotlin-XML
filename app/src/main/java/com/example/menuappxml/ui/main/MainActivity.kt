package com.example.menuappxml.ui.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.menuappxml.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navigationView)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val titleView = toolbar.findViewById<TextView>(R.id.toolbarTitle)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host)
                ?.findNavController()!!

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment),
            drawerLayout
        )

        NavigationUI.setupActionBarWithNavController(
            this,
            navController,
            appBarConfiguration
        )

        NavigationUI.setupWithNavController(navView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            titleView.text = when (destination.id) {
                R.id.homeFragment -> "Home"
                R.id.profileFragment -> "Profile"
                R.id.settingsFragment -> "Settings"
                R.id.detailFragment -> "Details"
                else -> "Menu App"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host)
                ?.findNavController()!!

        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}


package com.example.rickandmorty.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.ui.fragments.characters.CharacterFragment
import com.example.rickandmorty.ui.fragments.episodes.EpisodeFragment
import com.example.rickandmorty.ui.fragments.location.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.characterFragment -> setVisibilityNavBottom(true)
                R.id.episodeFragment -> setVisibilityNavBottom(true)
                R.id.locationFragment -> setVisibilityNavBottom(true)
                else -> setVisibilityNavBottom(false)
            }
        }
    }

    private fun setVisibilityNavBottom(isVisible: Boolean) {
        binding.bottomNavigation.apply {
            visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}
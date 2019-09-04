package com.juangm.moviesmvi.ui.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.juangm.moviesmvi.R
import kotlinx.android.synthetic.main.activity_main.*

class MoviesActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        moviesBottomNavigation.setupWithNavController(findNavController(R.id.moviesNavHostFragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.moviesNavHostFragment).navigateUp(appBarConfiguration)
    }
}

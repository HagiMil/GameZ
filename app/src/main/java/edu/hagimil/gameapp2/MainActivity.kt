package edu.hagimil.gameapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.hagimil.gameapp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //hides action bar
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        //always night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //bottom navigation view
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)


    }
}
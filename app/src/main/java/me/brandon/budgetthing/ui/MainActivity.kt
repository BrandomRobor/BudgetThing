package me.brandon.budgetthing.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import me.brandon.budgetthing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appToolbar)
        val navController =
            (supportFragmentManager.findFragmentById(binding.appFragmentContainer.id) as NavHostFragment).navController
        binding.appNavView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(binding.appNavView.menu, binding.appDrawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return binding.appFragmentContainer.findNavController()
            .navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

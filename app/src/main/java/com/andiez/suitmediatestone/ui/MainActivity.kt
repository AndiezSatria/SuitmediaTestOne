package com.andiez.suitmediatestone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.andiez.suitmediatestone.R
import com.andiez.suitmediatestone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController: NavController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
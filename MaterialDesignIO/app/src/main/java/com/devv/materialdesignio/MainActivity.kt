package com.devv.materialdesignio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devv.materialdesignio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nav = findNavController(R.id.fragment)
        val appBar = AppBarConfiguration(setOf(
            R.id.afragment,
            R.id.bfragment,
            R.id.cfragment,
            R.id.dfragment,
            R.id.ffragment
        ))
        setupActionBarWithNavController(nav, appBar)
        binding.bottomNavigation.setupWithNavController(nav)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
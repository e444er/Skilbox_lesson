package com.devv.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container, LoginFragment()).commit()
    }
    override fun onBackPressed() {
        supportFragmentManager.findFragmentById(
            R.id.container
        )?.childFragmentManager?.takeIf {
            it.backStackEntryCount > 0
        }?.popBackStackImmediate() ?: super.onBackPressed()
    }
}
package com.devv.toolbar

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main : AppCompatActivity(R.layout.main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolBar)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.back -> {
                    Toast.makeText(this, "back", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.wc -> {
                    Toast.makeText(this, "wc", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.view -> {
                    Toast.makeText(this, "view", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
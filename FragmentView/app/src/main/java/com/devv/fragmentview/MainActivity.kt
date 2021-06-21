package com.devv.fragmentview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.devv.fragmentview.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        binding.button.setOnClickListener {
            dialog()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, postion ->
            when (postion) {
                0 -> {
                    tab.text = Enum.SPORT.toString()
                }
                1 -> {
                    tab.text = Enum.BIZNES.toString()
                }
                2 -> {
                    tab.text = Enum.HEALTH.toString()
                }
            }
        }.attach()
    }

    private fun dialog() {
        val mailProviders =
            arrayOf(Enum.SPORT.toString(), Enum.BIZNES.toString(), Enum.HEALTH.toString())
        AlertDialog.Builder(this)
            .setTitle("Cтатьи")
            .setItems(mailProviders) { _, which ->
                toast("Selected = ${mailProviders[which]}")
            }.show()


    }

    private fun toast(test: String) {
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show()
    }
}
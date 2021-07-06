package com.devv.viewandlayout

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.devv.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.doOnTextChanged { text, start, before, count ->
            binding.button.isEnabled = text?.isNotEmpty() ?: false
        }

        binding.editText2.doOnTextChanged { text, start, before, count ->
            binding.button.isEnabled = text?.isNotEmpty() ?: false
        }

        binding.checkBox.setOnCheckedChangeListener { checkView, isChecked ->
            if (isChecked) {
                binding.button.isEnabled = true
            }
        }

        binding.button.setOnClickListener {
            if (binding.editText.toString().isNotEmpty() || binding.editText2.toString()
                    .isNotEmpty()
                || binding.checkBox.isChecked
            ) {
                click()
                Handler(Looper.getMainLooper()).postDelayed({
                    stop()
                }, 2000)
            } else {
                Toast.makeText(
                    this, R.string.toast,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun click() {
        binding.editText.isEnabled = false
        binding.editText2.isEnabled = false
        binding.checkBox.isEnabled = false
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stop() {
        binding.progressBar.visibility = View.GONE
        Toast.makeText(this, R.string.toast1, Toast.LENGTH_SHORT).show()
        binding.editText.isEnabled = true
        binding.editText2.isEnabled = true
        binding.checkBox.isEnabled = true
    }
}
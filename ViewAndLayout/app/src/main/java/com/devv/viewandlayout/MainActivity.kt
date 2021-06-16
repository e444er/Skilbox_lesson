package com.devv.viewandlayout

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devv.viewandlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = s?.let { it.isNotEmpty()  } ?: false
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.editText2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.button.isEnabled = s?.let { it.isNotEmpty()  } ?: false
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.checkBox.setOnCheckedChangeListener { checkView, isChecked ->
            if (isChecked){
                binding.button.isEnabled = true
            }
        }


        binding.button.setOnClickListener{
            if(binding.editText.toString().isNotEmpty() || binding.editText2.toString().isNotEmpty()
                || binding.checkBox.isChecked){
                click()
                Handler().postDelayed({
                    stop()
                }, 2000)
            }else{
                Toast.makeText(this, R.string.toast,
                    Toast.LENGTH_SHORT).show()
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
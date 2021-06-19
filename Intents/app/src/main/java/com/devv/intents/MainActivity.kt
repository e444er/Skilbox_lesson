package com.devv.intents

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.devv.intents.MainActivity2.Companion.KEY
import com.devv.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var getCall = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if(it){
            Toast.makeText(this, "permnission granted", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No permnission", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val login = binding.editText.text.toString()
            val isValid = Patterns.EMAIL_ADDRESS.matcher(login).matches()
            if (!isValid) {
                Toast.makeText(this, "Email error", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity2::class.java).putExtra(KEY, login)
                startActivity(intent)
            }
        }

        binding.button3.setOnClickListener {
            call()
            getCall.launch(Manifest.permission.CALL_PHONE)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    private fun call() {
        val number = binding.editText2.text.toString()
        val isValid = Patterns.PHONE.matcher(number).matches()
        if (!isValid) {
            Toast.makeText(this, "Number error", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$number")
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
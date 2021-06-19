package com.devv.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devv.intents.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val text = intent.getStringExtra(KEY)

        binding.textView.text = text

    }
    companion object{
        const val KEY = "message key"
    }
}
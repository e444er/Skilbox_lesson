package com.devv.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devv.intents.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding : ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        intentData()

    }
    private fun  intentData() {
        intent.data?.lastPathSegment?.let { name->
            binding.textView3.text = name

        }
    }
}
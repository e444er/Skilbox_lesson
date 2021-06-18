package com.devv.activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Contacts.SettingsColumns.KEY
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.devv.activitylifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tag = "MainActivity"
    private var state: FormState = FormState(true, "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable<FormState>(KEY) ?: error("Error")
        }

        binding.recents.setOnClickListener {
            finish()
        }

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextPersonName.text.toString())
                        .matches()
                )
                    binding.validation.isEnabled = true
                else {
                    binding.validation.isEnabled = false
                    binding.editTextTextPersonName.setError("Email ошибка")
                }
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })

        binding.button.setOnClickListener {
            Thread.sleep(10000)
        }
        binding.button3.setOnClickListener {
            Thread.sleep(10000)
        }
        Log.v(tag, "onCreate")
        Log.d(tag, "onCreate")
        Log.i(tag, "onCreate")
        Log.e(tag, "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY, state)

    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }

    companion object {
        private const val KEY = "KEY"
    }
}

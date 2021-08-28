package com.devv.materialdesignio

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.devv.materialdesignio.databinding.CFragmentBinding
import com.google.android.material.snackbar.Snackbar

class CFragment: Fragment(R.layout.c_fragment) {
    private var _binding: CFragmentBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = CFragmentBinding.bind(view)
        binding.button.setOnClickListener {
            Snackbar.make(view, "Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action"){
                Toast.makeText(requireContext(), "Snackbar", Toast.LENGTH_SHORT).show()
            }.show()
        }
        binding.switch2.setOnClickListener {
            if (binding.switch2.isChecked) {
                Toast.makeText(requireContext(), "on", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "off", Toast.LENGTH_SHORT).show()
            }
        }
        binding.switch3.setOnClickListener{
            if(binding.switch2.isChecked){
                    context?.getColor(R.color.black)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
//                context?.getColor(R.color.white)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
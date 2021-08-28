package com.devv.materialdesignio

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.devv.materialdesignio.databinding.BFragmentBinding
import com.google.android.material.chip.Chip

class BFragment: Fragment(R.layout.b_fragment) {
    private var _binding: BFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = BFragmentBinding.bind(view)
        binding.chip6.setOnClickListener {
            Toast.makeText(requireContext(), binding.chip6.text, Toast.LENGTH_SHORT).show()
        }
        entryChip()
        chipGroups()
        chipFilter()
    }

    private fun chipFilter() {
        binding.chipFilter.setOnCheckedChangeListener { group, checkedId ->
            val chip:Chip? = group.findViewById(checkedId)
            if(chip?.isChecked == true){
                Toast.makeText(requireContext(), chip.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun chipGroups() {
        binding.chipGroups.setOnCheckedChangeListener { group, checkedId ->
            val chip:Chip? = group.findViewById(checkedId)
            chip?.let{
                Toast.makeText(requireContext(), it.text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun entryChip() {
        binding.editText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER &&
                    event.action == KeyEvent.ACTION_UP){
                        binding.apply {
                            val name = editText.text.toString()
                            createChips(name)
                            editText.text.clear()
                        }
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun createChips(name: String) {
        val chip = Chip(requireContext())
        chip.apply {
            text = name
            checkedIcon = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_dark_mode_24
            )
            isChipIconVisible = false
            isCloseIconVisible = true
            isCheckable =true
            isCheckable =true
            binding.apply {
                chipEnGroup.addView(chip as View)
                chip.setOnCloseIconClickListener {
                    chipEnGroup.removeView(chip as View)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
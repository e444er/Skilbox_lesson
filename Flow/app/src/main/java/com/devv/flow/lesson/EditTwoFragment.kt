package com.devv.flow.lesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.flow.R
import com.devv.flow.databinding.EditTwoFragmentBinding
import com.devv.flow.lesson.exfun.textChangeFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EditTwoFragment : Fragment(R.layout.edit_two_fragment) {

    private val binding: EditTwoFragmentBinding by viewBinding(EditTwoFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flowCallback()
    }

    private fun flowCallback() {
        viewLifecycleOwner.lifecycleScope.launch {
            binding.editTextTwo.textChangeFlow().collect {
                binding.textView2.text = it
            }
        }
    }
}
package com.devv.flow.lesson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.flow.R
import com.devv.flow.databinding.FlowBasicFragmentBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

class FlowBasicFragment : Fragment(R.layout.flow_basic_fragment) {

    private val binding: FlowBasicFragmentBinding by viewBinding(FlowBasicFragmentBinding::bind)
    private val job: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val generator = createFlowGenerator()

        binding.button.setOnClickListener {
            job?.cancel()
            viewLifecycleOwner.lifecycleScope.launch {
                generator
                    .collect {
                        binding.textView.text = it.toString()
                    }
            }
        }
    }

    private fun createFlowGenerator(): Flow<Int> {
        return flow {
            while (true) {
                val value = Random.nextInt()
                emit(value)
                delay(1000)
            }
        }
    }
}
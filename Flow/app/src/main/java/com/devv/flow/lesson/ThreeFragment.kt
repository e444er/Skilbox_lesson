package com.devv.flow.lesson

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.flow.R
import com.devv.flow.databinding.ThreeFragmentBinding
import com.devv.flow.lesson.exfun.checkboxChangesFlow
import com.devv.flow.lesson.exfun.textChangeFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException

class ThreeFragment : Fragment(R.layout.three_fragment) {

    private val TAG = "tag"

    private val users = listOf(
        User(1, "Петр", 12, "male"),
        User(2, "Олег", 34, "male"),
        User(3, "Федор", 23, "male"),
        User(4, "Иван", 21, "male"),
        User(5, "Саша", 46, "male"),
        User(6, "Паша", 11, "male")
    )

    private val binding: ThreeFragmentBinding by viewBinding(ThreeFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flowOperators()
    }

    private fun flowOperators() {
        viewLifecycleOwner.lifecycleScope.launch {

            combine(
                binding.checkBox.checkboxChangesFlow().onStart { emit(false) },
                binding.editTextthree.textChangeFlow().onStart { emit("") },
                { onlyFemaile, text -> onlyFemaile to text }
            )
                .debounce(500)
                .distinctUntilChanged()
                .onEach { showProgress(true) }
                .mapLatest { (onlyFemaile, text) -> searchUsers(onlyFemaile, text) }
                .onEach { showProgress(false) }
                .map { it.map { it.toString() }.joinToString { "\n" } }
                .collect {
                    binding.textView3.text = it
                }
        }
    }

    private suspend fun searchUsers(onlyFemaile: Boolean, query: String): List<User> {
        delay(1000)
        return users.filter {
            val isGender = (!onlyFemaile) || it.gender == "male"
            it.name.contains(query, ignoreCase = true) && isGender
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.isVisible = show
        binding.textView3.isVisible = !show
    }
    private fun errorHandler(){
        flow{
            delay(1000)
            throw  IOException("network")
            emit(1)
        }
            .catch { emit(-1) }
            .map{it * 2}
            .catch { Log.d(TAG, "$it") }
            .map { error("test") }
            .catch { Log.d(TAG, "2 = $it")  }
            .onEach { Log.d(TAG, "onEach = $it") }
            .catch { Log.d(TAG, "3 =$it")  }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
package com.devv.files

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devv.files.databinding.WorkFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class WorkFragment : Fragment(R.layout.work_fragment) {

    private lateinit var binding: WorkFragmentBinding
    val sharedPrefs by lazy {
        requireContext().getSharedPreferences(SHARED_NAME,
            Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WorkFragmentBinding.bind(view)



        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val edit = binding.editText.text.toString()
                sharedPrefs.edit().putString(KEY, edit).apply()
            }
            dowFile()
        }
    }

    private fun dowFile() {
        lifecycleScope.launch(Dispatchers.IO) {
            val edit = binding.editText.text.toString()
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
            val testFolder = requireContext().getExternalFilesDir("/storage/files/$edit")
            val testFile = File(testFolder, "1601841925_README.md")
            try {
                testFile.outputStream().use { fileOutputStream ->
                    Networking.api.getFile("https://gitlab.skillbox.ru/bauyrzhan_tastanbekov/android_basic/-/blob/master/README.md")
                        .byteStream()
                        .use { inputStream ->
                            inputStream.copyTo(fileOutputStream)
                        }
                }
            } catch (t: Throwable) {
                testFile.delete()
            }
        }
    }

    companion object {
        private const val SHARED_NAME = "1601841925_README.md"
        private const val KEY =
            "https://gitlab.skillbox.ru/bauyrzhan_tastanbekov/android_basic/-/blob/master/README.md"
    }
}
package com.devv.files


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devv.files.databinding.WorkFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*

class WorkFragment : Fragment(R.layout.work_fragment) {

    private lateinit var binding: WorkFragmentBinding
    val sharedPrefs by lazy {
        requireContext().getSharedPreferences(SHARED_NAME,
            Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = WorkFragmentBinding.bind(view)


        val url = binding.editText.text.toString()
        binding.button.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                val timesTamp = Date().time / 1000
                val fileName = url.substringAfterLast("/").substringAfterLast(".")
                if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
                val testFolder = requireContext().getExternalFilesDir("storage/files/external")
                val testFile = File(testFolder, "${timesTamp}_$fileName.md")
                try {
                    testFile.outputStream().use { fileOutputStream ->
                        Networking.api.getFile("$url")
                            .byteStream()
                            .use { inputStream ->
                                inputStream.copyTo(fileOutputStream)
                            }
                    }

                } catch (t: Throwable) {
                    testFile.delete()
                }

                sharedPrefs.edit().putString(KEY, testFile.toString()).apply()
            }
        }
        val listemer =
            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
                update()
            }
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.registerOnSharedPreferenceChangeListener(listemer)
        }
    }

    fun update() {
        lifecycleScope.launch(Dispatchers.IO) {
            sharedPrefs.getString(KEY, null)
        }
    }

    companion object {
        private const val SHARED_NAME = "1601841925_README.md"
        private const val KEY = "key"
    }
}
package com.devv.contentprovider

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.devv.contentprovider.databinding.ShareBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class Share : Fragment(R.layout.share) {
    private lateinit var binding: ShareBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ShareBinding.bind(view)
        binding.button3.setOnClickListener {
            share()
        }
    }

    private fun share() = lifecycleScope.launch(Dispatchers.IO) {
        if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) return@launch
        val dir = requireContext().getExternalFilesDir("saved_file")
        val file = File(dir, "text.txt")
        file.createNewFile()
        if (file.exists().not()) return@launch
        file.outputStream().buffered().use {
            it.write("Hi Федор".toByteArray())
        }

        val uri = FileProvider.getUriForFile(requireContext(),
            "${BuildConfig.APPLICATION_ID}.fileProvider",
            file)
        val intent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_STREAM, uri)
            type = requireContext().contentResolver.getType(uri)
            setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }
}
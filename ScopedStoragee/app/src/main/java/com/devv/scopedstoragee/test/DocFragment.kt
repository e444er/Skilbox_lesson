package com.devv.scopedstoragee.test

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.scopedstoragee.R
import com.devv.scopedstoragee.databinding.DocFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DocFragment : Fragment(R.layout.doc_fragment) {

    private val binding: DocFragmentBinding by viewBinding(DocFragmentBinding::bind)

    private lateinit var openDoc: ActivityResultLauncher<Array<String>>
    private lateinit var createDoc: ActivityResultLauncher<String>
    private lateinit var secDoc: ActivityResultLauncher<Uri>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initOpenDoc()
        initCreateDoc()
        initSecDoc()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button5.setOnClickListener {
            readFile()
        }
        binding.button6.setOnClickListener {
            createFile()
        }
        binding.button7.setOnClickListener {
            secondaryFile()
        }
    }

    private fun initOpenDoc() {
        openDoc = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { uri ->
            handlerDoc(uri)
        }
    }

    private fun initCreateDoc() {
        createDoc = registerForActivityResult(
            ActivityResultContracts.CreateDocument()
        ) { uri ->
            handlerCreateDoc(uri)
        }
    }

    private fun initSecDoc() {
        secDoc = registerForActivityResult(
            ActivityResultContracts.OpenDocumentTree()
        ) { uri ->
            handlerSecDoc(uri)
        }
    }

    private fun readFile() {
        openDoc.launch(arrayOf("text/plain"))
    }

    private fun createFile() {
        createDoc.launch("text.txt")
    }

    private fun secondaryFile() {
        secDoc.launch(null)
    }

    private fun handlerDoc(uri: Uri?) {
        if (uri == null) {
            Toast.makeText(requireContext(), "not selected", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                requireContext().contentResolver.openInputStream(uri)
                    ?.bufferedReader()
                    ?.use {
                        it.readLines().joinToString(
                            Log.d("bufferedReader", "read content").toString()
                        )
                    }
            }
        }
    }

    private fun handlerCreateDoc(uri: Uri?) {
        if (uri == null) {
            Toast.makeText(requireContext(), "not created", Toast.LENGTH_SHORT).show()
            return
        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                requireContext().contentResolver.openOutputStream(uri)
                    ?.bufferedWriter()
                    ?.use {
                        it.write("Test content")
                    }
            }
        }
    }

    private fun handlerSecDoc(uri: Uri?) {
        if (uri == null) {
            Toast.makeText(requireContext(), "not Sec", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(requireContext(), "n = $uri", Toast.LENGTH_SHORT).show()

    }

}
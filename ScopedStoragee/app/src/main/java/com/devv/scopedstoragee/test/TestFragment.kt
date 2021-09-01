package com.devv.scopedstoragee.test

import android.Manifest
import android.app.Activity
import android.app.RemoteAction
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devv.scopedstoragee.R
import com.devv.scopedstoragee.databinding.TestFragmentBinding

class TestFragment : Fragment(R.layout.test_fragment) {

    private val binding: TestFragmentBinding by viewBinding(TestFragmentBinding::bind)
    private var imageAdapter: ImageAdapter? = null
    private val viewModel: ImageViewModel by viewModels()
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var recLauncher: ActivityResultLauncher<IntentSenderRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPermissionResultListener()
        initRecListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        requestPermissions()
        bindViewModel()
        initCallback()
        imageAdapter?.setOnItemClickCallback(object : ImageAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Image) {
                viewModel.deleteImage(data.id)
            }
        })
        if (hasPermission().not()) {
            requestPermissions()
        }
    }

    private fun initCallback() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_testFragment_to_addFragment)
        }
    }

    private fun bindViewModel() {
        viewModel.imagesLiveData.observe(viewLifecycleOwner,
            { imageAdapter?.differ?.submitList(it) })
        viewModel.permissionsGLData.observe(viewLifecycleOwner, ::updateState)
        viewModel.recMutableLiveData.observe(viewLifecycleOwner, ::recAction)
    }

    private fun hasPermission(): Boolean {
        return PERMISSIONS.all {
            ActivityCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun recAction(action: RemoteAction) {
        val request = IntentSenderRequest.Builder(action.actionIntent.intentSender)
            .build()
        recLauncher.launch(request)
    }

    private fun updateState(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }


    override fun onResume() {
        super.onResume()
        viewModel.updatePermissionState(hasPermission())
    }

    private fun initList() {
        imageAdapter = ImageAdapter()
        with(binding.recyclerView) {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun initRecListener() {
        recLauncher = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { activityResult ->
            val isConn:Boolean = activityResult.resultCode == Activity.RESULT_OK
            if (isConn) {
                viewModel.configDeleteId()
            } else {
                viewModel.declineDelete()
            }
        }
    }

    private fun initPermissionResultListener() {
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionToGranteMap: Map<String, Boolean> ->
            if (permissionToGranteMap.values.all { it }) {
                viewModel.permissionGranted()
            } else {
                viewModel.permissionDenied()
            }

        }
    }

    private fun requestPermissions() {
        requestPermissionLauncher.launch(*PERMISSIONS.toTypedArray())
    }

    companion object {
        private val PERMISSIONS = listOfNotNull(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
                .takeIf { haveQ().not() }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        imageAdapter = null
    }
}
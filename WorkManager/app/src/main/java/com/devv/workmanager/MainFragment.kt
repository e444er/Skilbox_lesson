package com.devv.workmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.work.*
import com.devv.workmanager.databinding.MainFragmentBinding

class MainFragment : Fragment(R.layout.main_fragment){
    private var _binding:MainFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = MainFragmentBinding.bind(view)
        binding.button2.setOnClickListener {
            download()
        }

        binding.button3.setOnClickListener {
            WorkManager.getInstance(requireContext()).cancelAllWork()
        }
       }

    private fun download() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(requireContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), CODE)
            }
            else{
                startDownload()
            }
        }
        else{
            startDownload()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED)
                    startDownload()
            }
            else  -> Toast.makeText(requireContext(), "Permission отмена", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        const val CODE = 1000
    }

    private fun startDownload() {
        val urlDownload = binding.editTextTextPersonName.text.toString()

        val workData = workDataOf(
            DownloadWorker.DOWNLOAD_URL_KEY to urlDownload
        )

        val workCons = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val workRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(workCons)
            .setInputData(workData)
            .build()

        WorkManager.getInstance(requireContext())
            .enqueue(workRequest)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.devv.contentprovider

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devv.contentprovider.databinding.AddFragmentBinding
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class AddFragment : Fragment(R.layout.add_fragment) {
    private var binding: AddFragmentBinding? = null
    private val viewModel by viewModels<AddViewmodel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        binding?.button?.setOnClickListener {
            constructPermissionsRequest(
                Manifest.permission.WRITE_CONTACTS,
                onShowRationale = ::onContactPermissonsShowRatinale,
                onPermissionDenied = ::onContactPermissonDenied,
                onNeverAskAgain = ::onContactPermissonNeverAskAgain){
                viewModel.save(
                    name = binding?.editTextTextPersonName?.text?.toString().orEmpty(),
                    number = binding?.editTextTextPersonName2?.text?.toString().orEmpty()
                )
            }.launch()
        }
    }

    private fun bindViewModel() {
        viewModel.saveSuccess.observe(viewLifecycleOwner) {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }
    }

    private fun onContactPermissonsShowRatinale(request: PermissionRequest) {
        request.proceed()
    }

    private fun onContactPermissonDenied() {
        Toast.makeText(requireContext(), "onContactPermissonDenied", Toast.LENGTH_SHORT).show()
    }

    private fun onContactPermissonNeverAskAgain() {
        Toast.makeText(requireContext(), "onContactPermissonNeverAskAgain", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
package com.devv.contentprovider


import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.contentprovider.databinding.ListFragmentBinding
import permissions.dispatcher.PermissionRequest
import permissions.dispatcher.ktx.constructPermissionsRequest

class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel: ListViewModel by viewModels<ListViewModel>()
    private var adapterList: ListAdapter? = null
    private lateinit var binding: ListFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        initList()
        bindViewModel()
        binding.button2.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToShare())
        }
        binding.floatingActionButton.setOnClickListener {
            floButton()
        }
        Handler(Looper.getMainLooper()).post {
            constructPermissionsRequest(
                Manifest.permission.READ_CONTACTS,
                onShowRationale = ::onContactPermissonsShowRatinale,
                onPermissionDenied = ::onContactPermissonDenied,
                onNeverAskAgain = ::onContactPermissonNeverAskAgain,
                requiresPermission = { viewModel.loadList() }
            )
                .launch()
        }
    }

    private fun floButton() {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
    }

    private fun initList() {
        adapterList = ListAdapter()
        with(binding.recyclerview) {
            adapter = adapterList
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.contactLiveData.observe(viewLifecycleOwner) { adapterList?.differ?.submitList(it) }
        viewModel.callLiveData.observe(viewLifecycleOwner, ::callToPhone)
    }

    private fun callToPhone(phone: String) {
        Intent(Intent.ACTION_DIAL)
            .apply { data = Uri.parse("tel:$phone") }
            .also { startActivity(it) }
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
        adapterList = null
    }

}
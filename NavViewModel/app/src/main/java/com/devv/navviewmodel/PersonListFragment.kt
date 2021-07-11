package com.devv.navviewmodel


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devv.navviewmodel.databinding.ListFragmentBinding

class PersonListFragment : Fragment(R.layout.list_fragment) {

    private lateinit var binding: ListFragmentBinding

    private val personListViewModel: PersonListViewModel by viewModels()

    private var personAdapter: PersonAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListFragmentBinding.bind(view)
        initList()
        binding.floatingActionButton.setOnClickListener { adduser() }
        observeViewModelState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
    }

    private fun initList() {
        personAdapter = PersonAdapter { id ->
            val action =
                PersonListFragmentDirections.actionPersonListFragmentToDetailsFragment(id)
            findNavController().navigate(action)
        }
        with(binding.userList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun deletePerson(position: Int) {
        personListViewModel.deletePerson(position)
    }

    private fun adduser() {
        personListViewModel.addPerson()
        binding.userList.scrollToPosition(0)
    }


    private fun observeViewModelState() {
        personListViewModel.persons
            .observe(viewLifecycleOwner) { newPersons ->
                personAdapter?.items = newPersons
            }
        personListViewModel.showToast
            .observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), "Добавлен", Toast.LENGTH_SHORT).show()
            }
    }
}
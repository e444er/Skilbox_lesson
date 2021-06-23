package com.devv.lists_2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.devv.lists_2.databinding.FragmentImageBinding

class ImageListFragment : Fragment(R.layout.fragment_image) {
    private lateinit var binding: FragmentImageBinding
    private val images = listOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
        "https://cdn.pixabay.com/photo/2015/12/01/20/28/road-1072823_960_720.jpg",
        "https://cdn.pixabay.com/photo/2015/06/19/21/24/avenue-815297__340.jpg",
        "https://cdn.pixabay.com/photo/2017/02/08/17/24/fantasy-2049567__340.jpg",
        "https://cdn.pixabay.com/photo/2014/09/07/22/17/forest-438432__340.jpg",
        "https://cdn.pixabay.com/photo/2017/01/14/12/59/iceland-1979445__340.jpg",
        "https://cdn.pixabay.com/photo/2017/02/07/16/47/kingfisher-2046453__340.jpg"

    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageBinding.bind(view)
        initList()
        binding.button.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.frame,PersonListFragment())?.commit()
        }
    }

    private fun initList() = with(binding.imageList) {
        adapter = ImageAdapter().apply {
            setImage(images + images + images)
        }
        setHasFixedSize(true)

        addItemDecoration(ItemOfsetDec(requireContext()))
        layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }
}
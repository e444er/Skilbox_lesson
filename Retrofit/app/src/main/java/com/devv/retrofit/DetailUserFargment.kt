package com.devv.retrofit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.devv.retrofit.databinding.DetailUserFragmnetBinding

class DetailUserFargment : Fragment(R.layout.detail_user_fragmnet) {

    private lateinit var binding: DetailUserFragmnetBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailUserFargmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailUserFragmnetBinding.bind(view)
        val username = args.username
        viewModel.setUserDetail(username)
        viewModel.getdetailList.observe(viewLifecycleOwner,
            {
                bindUi(it)
            })
    }

    fun bindUi(it: List<Detail>?) {
        if (it != null) {
            binding.apply {
                textView2.text = it.login
                textView3.text = it.name
                textView4.text = "${it.followers} Followers"
                textView5.text = "${it.following} Following"
                Glide.with(binding.root)
                    .load(it.avatar_url)
                    .into(imageView2)
            }
        }
    }
}


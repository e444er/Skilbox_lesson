package com.devv.fragmentview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragment, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                BlankFragment()
            }
            1 -> {
                BlankFragment2()
            }
            2 -> {
                BlankFragment3()
            }
            else -> {

                BlankFragment()
            }
        }
    }
}
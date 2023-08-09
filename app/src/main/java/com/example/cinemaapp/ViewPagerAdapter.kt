package com.example.cinemaapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity):FragmentStateAdapter(activity) {
    var fragments= listOf(HomeFragment(),ComingFragment())

    override fun getItemCount()=fragments.size

    override fun createFragment(position: Int): Fragment =fragments[position]
}
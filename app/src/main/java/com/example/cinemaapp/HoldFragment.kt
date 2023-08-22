package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemaapp.databinding.FragmentHoldBinding
import com.google.android.material.tabs.TabLayoutMediator


class HoldFragment : Fragment() {
    lateinit var binding: FragmentHoldBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpTabLayout()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoldBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun setUpViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(this.requireActivity())
        binding.ViewPager.adapter = viewPagerAdapter

    }

    fun setUpTabLayout() {
        val tabNames = listOf("Playing Now", "Coming Soon")
        TabLayoutMediator(binding.tabs, binding.ViewPager) { tab, positon ->
            tab.text = tabNames[positon]
        }.attach()
    }
}
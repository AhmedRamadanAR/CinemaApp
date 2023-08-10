package com.example.cinemaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentBasicBinding
import com.example.cinemaapp.databinding.FragmentHoldBinding
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HoldFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HoldFragment : Fragment() {
    lateinit var binding: FragmentHoldBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpTabLayout()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
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
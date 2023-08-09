package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
        setUpTabLayout()
    }
    fun setUpViewPager(){
   val viewPagerAdapter=ViewPagerAdapter(this)
        binding.ViewPager.adapter=viewPagerAdapter
    }
    fun setUpTabLayout(){
        val tabNames= listOf("Playing Now","Coming Soon")
        TabLayoutMediator(binding.tabs,binding.ViewPager){tab,positon->
            tab.text=tabNames[positon]
        }.attach()
    }
}
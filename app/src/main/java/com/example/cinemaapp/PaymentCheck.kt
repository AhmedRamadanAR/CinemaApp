package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemaapp.databinding.FragmentMovieDetailsBinding
import com.example.cinemaapp.databinding.FragmentPaymentCheckBinding

class PaymentCheck : Fragment() {

    private lateinit var binding: FragmentPaymentCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentCheckBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpView()

    }

    private fun setUpView() {

        val count = arguments?.getString("quantity")
        val type = arguments?.getString("type")
        val price = arguments?.getString("price")

//        val count1 = arguments?.getString("quantity1")
//        val type1 = arguments?.getString("type1")
//        val price1 = arguments?.getString("price1")
//        Log.d("COME", "setUpView: $count")

        binding.tvPopcornCount.text = count
        binding.tvPopcornPrice.text = price+"$"
//
//        binding.tvDrinkCount.text = count1
//        binding.tvDrinkPrice.text = price1+"$"


    }


}
package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        binding.btnBuy.setOnClickListener {

            findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck)


        }

    }

    private fun setUpView() {

        val count = arguments?.getString("quantity")
        val type = arguments?.getString("type")
        val price = arguments?.getString("price")

        val count1 = arguments?.getString("quantity1")
        val type1 = arguments?.getString("type1")
        val price1 = arguments?.getString("price1")

        val count2 = arguments?.getString("quantity2")
        val type2 = arguments?.getString("type2")
        val price2 = arguments?.getString("price2")
//        Log.d("COME", "setUpView: $count")

        binding.tvPopcornCount.text = count
        binding.tvPopcornPrice.text = price+"$"

        binding.tvDrinkCount.text = count1
        binding.tvDrinkPrice.text = price1+"$"
//
        binding.tvChipsCount.text = count2
        binding.tvChipsPrice.text = price2+"$"

        binding.tvTotalQuantityCount.text = (count!!.toInt()+count1!!.toInt()+count2!!.toInt()).toString()

        binding.tvTotalPriceMoney.text = (price!!.toDouble()+price1!!.toDouble()+price2!!.toDouble()).toString()+"$"



    }


}
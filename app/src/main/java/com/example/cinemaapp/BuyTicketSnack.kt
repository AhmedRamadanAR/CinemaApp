package com.example.cinemaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.FragmentBuyTicketSnackBinding
import com.example.cinemaapp.model.SnakeItemData


class BuyTicketSnack : Fragment() {

    private lateinit var binding: FragmentBuyTicketSnackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBuyTicketSnackBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var snake = mutableListOf(
            SnakeItemData(
                "popcorn",
                R.drawable.popcorn,
                0,
                0,
                35
            ),
            SnakeItemData(
                "pepsi",
                R.drawable.can2,
                1,
                0,
                50
            ),
            SnakeItemData(
                "chips",
                R.drawable.chips,
                2,
                0,
                100
            ),

            )

        val adapterCategory = SnacksAdapter(snake)
        view.findViewById<RecyclerView>(R.id.rv_snack1).adapter = adapterCategory
        view.findViewById<RecyclerView>(R.id.rv_snack1).layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        var by = true
        var bn = true
        binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)

        binding.btnYes.setOnClickListener {

            if (by) {

                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue_border)
                binding.rvSnack1.visibility = View.VISIBLE

                by = false
                bn = true

            }
//            else {
//                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue_border)
//                binding.rvSnack1.visibility = View.GONE
//
//                by = true
//            }
        }

        binding.btnNo.setOnClickListener {

            if (bn) {

                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue_border)
                binding.rvSnack1.visibility = View.GONE

                bn = false
                by = true

            }
//            else {
//                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue_border)
//                binding.rvSnack1.visibility = View.GONE
//
//                bn = true
//            }
        }


        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck)
        }


    }




}
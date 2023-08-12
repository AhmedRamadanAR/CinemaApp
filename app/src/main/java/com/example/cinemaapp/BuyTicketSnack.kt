package com.example.cinemaapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.get
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
                35.0,
                0.0
            ),
            SnakeItemData(
                "pepsi",
                R.drawable.can2,
                1,
                0,
                50.0,
                0.0
            ),
            SnakeItemData(
                "chips",
                R.drawable.chips,
                2,
                0,
                100.0,
                0.0
            ),

            )

        val adapterCategory = SnacksAdapter(snake)
        view.findViewById<RecyclerView>(R.id.rv_snack1).adapter = adapterCategory
        view.findViewById<RecyclerView>(R.id.rv_snack1).layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val currentCount = adapterCategory.getCurrentCount(0)


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
//            Log.d("Clicked100", "HELLO ${currentCount}")
//            Log.d("Clicked100", "HELLO ${snake.get(0).snakeCount}")

//            Log.d("Clicked100", "HELLO ${snake.get(0).type}")
//            Log.d("Clicked100", "HELLO ${snake.get(0).snakeCount}")
//            Log.d("Clicked100", "HELLO ${snake.get(0).snakePrice}")
//            Log.d("Clicked100", "HELLO ${snake.get(1).type}")
//            Log.d("Clicked100", "HELLO ${snake.get(1).snakeCount}")
//            Log.d("Clicked100", "HELLO ${snake.get(1).snakePrice}")
            val info = bundleOf(
                "quantity" to snake.get(0).snakeCount.toString(),
                "type" to snake.get(0).type.toString(),
                "price" to snake.get(0).snakePriceEdit.toString(),

                "quantity1" to snake.get(1).snakeCount.toString(),
                "type1" to snake.get(1).type.toString(),
                "price1" to snake.get(1).snakePriceEdit.toString(),

                "quantity2" to snake.get(2).snakeCount.toString(),
                "type2" to snake.get(2).type.toString(),
                "price2" to snake.get(2).snakePriceEdit.toString(),

//                "quantity1" to snake.get(1).snakeCount.toString(),
//                "type1" to snake.get(1).type.toString(),
//                "price1" to snake.get(1).snakePrice.toString()
            )
//            Log.d("COME100", "HELLO ${snake.get(0).snakeCount}")
            findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck,info)
        }

//        val info = bundleOf(
////            "type" to snake.get(0).type,
//            "quantity" to snake.get(0).snakeCount
////            "price" to binding.rvSnack1.get(0).findViewById<TextView>(R.id.tv_price).text,
////            "quantity" to binding.rvSnack1.get(0).findViewById<TextView>(R.id.tv_quantity).text
//
//
//        )



    }


}
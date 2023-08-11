package com.example.cinemaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.model.SnakeItemData

class Snacks : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_snacks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var snake = mutableListOf(
            SnakeItemData(
                R.drawable.popcorn,
                0,
                0,
                35
            ),
            SnakeItemData(
                R.drawable.can2,
                1,
                0,
                50
            ),
            SnakeItemData(
                R.drawable.chips,
                2,
                0,
                100
            ),

        )

        val adapterCategory = SnakeItemAdapter(snake)
        view.findViewById<RecyclerView>(R.id.rv_snack) .adapter = adapterCategory
        view.findViewById<RecyclerView>(R.id.rv_snack) .layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

}
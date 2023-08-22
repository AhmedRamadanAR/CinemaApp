package com.example.cinemaapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.model.SnakeItemData

class TicketAdapter(private val tickets: List<SnakeItemData>) :
    RecyclerView.Adapter<TicketAdapter.TicketItemViewHolder>() {


    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: TicketAdapter.TicketItemViewHolder, position: Int) {

        holder.itemView.findViewById<Button>(R.id.btn_small).visibility=View.GONE
        holder.itemView.findViewById<Button>(R.id.btn_medium).visibility=View.GONE
        holder.itemView.findViewById<Button>(R.id.btn_large).visibility=View.GONE


        var image = holder.itemView.findViewById<ImageView>(R.id.iv_food)
        var price = holder.itemView.findViewById<TextView>(R.id.tv_price)
        var count = holder.itemView.findViewById<TextView>(R.id.tv_quantity)
        var add = holder.itemView.findViewById<Button>(R.id.btn_add)
        var sub = holder.itemView.findViewById<Button>(R.id.btn_subtract)

        if (image != null && price != null && count != null) {
            image.setImageResource(tickets[position].snakeImage)

            price.text = tickets[position].snakePrice.toString()
            count.text = tickets[position].snakeCount.toString()
        }

        if (count.text.toString().toInt() == 0) {
            price.text = 0.toString()
        }


        add.setOnClickListener {
            var o = tickets[position].snakePrice
            if (count.text.toString().toInt() < 10) {
                count.text = count.text.toString().toInt().inc().toString()
                tickets.get(position).snakeCount = count.text.toString().toInt()
                Log.d("ITME", "onBindViewHolder: ${count.text}")
                price.text =
                    price.text.toString().toDouble().plus(o)
                        .toString()
                tickets.get(position).snakePriceEdit = price.text.toString().toDouble()
            }
        }






        sub.setOnClickListener {
            var o = tickets[position].snakePrice
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    tickets.get(position).snakeCount = count.text.toString().toInt()
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - o > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(o)
                            .toString()
                    tickets.get(position).snakePriceEdit = price.text.toString().toDouble()

                }
            }







    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketAdapter.TicketItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.snack_item, parent, false)
        return TicketItemViewHolder(view)

    }


    override fun getItemCount() = tickets.size

    inner class TicketItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}


}
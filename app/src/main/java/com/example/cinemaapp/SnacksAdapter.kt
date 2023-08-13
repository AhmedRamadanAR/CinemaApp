package com.example.cinemaapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.model.SnakeItemData
import com.example.cinemaapp.model.Ticket

class SnacksAdapter(private val snakes: List<SnakeItemData>) :
    RecyclerView.Adapter<SnacksAdapter.SnakeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnakeItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.snack_item, parent, false)
        return SnakeItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: SnakeItemViewHolder, position: Int) {

        var image = holder.itemView.findViewById<ImageView>(R.id.iv_food)
        var size = holder.itemView.findViewById<LinearLayout>(R.id.linearLayout_btn_sizes)
        var price = holder.itemView.findViewById<TextView>(R.id.tv_price)
        var count = holder.itemView.findViewById<TextView>(R.id.tv_quantity)
        var add = holder.itemView.findViewById<Button>(R.id.btn_add)
        var sub = holder.itemView.findViewById<Button>(R.id.btn_subtract)
        var yesno = holder.itemView.findViewById<LinearLayout>(R.id.linearLayout_btn_yes_no)

        if (image != null && price != null && count != null) {
            image.setImageResource(snakes[position].snakeImage)

            price.text = snakes[position].snakePrice.toString()
            count.text = snakes[position].snakeCount.toString()
        }

        if (count.text.toString().toInt() == 0) {
            price.text = 0.toString()
        }

        var by = true
        var bn = true

        var bs = true
        var bm = true
        var bl = true

        size.findViewById<Button>(R.id.btn_small).setOnClickListener {
            price.text = 0.toString()
            count.text = 0.toString()

            if (bs) {
                size.findViewById<Button>(R.id.btn_small)
                    .setBackgroundResource(R.drawable.rectangle_blue)

                size.findViewById<Button>(R.id.btn_medium)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                size.findViewById<Button>(R.id.btn_large)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)

                bs = false
                bm = true
                bl = true
            } else {
                size.findViewById<Button>(R.id.btn_small)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                bs = true
            }
        }

        size.findViewById<Button>(R.id.btn_medium).setOnClickListener {
            price.text = 0.toString()
            count.text = 0.toString()

            if (bm) {
                size.findViewById<Button>(R.id.btn_medium)
                    .setBackgroundResource(R.drawable.rectangle_blue)

                size.findViewById<Button>(R.id.btn_small)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                size.findViewById<Button>(R.id.btn_large)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)

                bm = false
                bs = true
                bl = true
            } else {
                size.findViewById<Button>(R.id.btn_medium)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                bm = true
            }
        }

        size.findViewById<Button>(R.id.btn_large).setOnClickListener {
            price.text = 0.toString()
            count.text = 0.toString()

            if (bl) {
                size.findViewById<Button>(R.id.btn_large)
                    .setBackgroundResource(R.drawable.rectangle_blue)
                size.findViewById<Button>(R.id.btn_medium)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                size.findViewById<Button>(R.id.btn_small)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)

                bl = false
                bs = true
                bm = true
            } else {
                size.findViewById<Button>(R.id.btn_large)
                    .setBackgroundResource(R.drawable.rectangle_blue_border)
                bl = true
            }
        }


        add.setOnClickListener {
            var o = snakes[position].snakePrice
            if (!bs) {
                val x = 35
                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()
                    Log.d("ITME", "onBindViewHolder: ${count.text}")
                    price.text =
                        price.text.toString().toDouble().plus(o)
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()
                }
            } else if (!bm) {

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()

                    price.text =
                        price.text.toString().toDouble()
                            .plus(o + (o * .1))
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()
                }

            } else if (!bl) {

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()

                    price.text =
                        price.text.toString().toDouble()
                            .plus(o + (o * .2))
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()
                }
            }
        }

        sub.setOnClickListener {
            var o = snakes[position].snakePrice
            if (!bs) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()
                    Log.d("ITME", "onBindViewHolder: ${snakes.get(position).snakeCount}")
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - o > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(o)
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()

                }
            }

            if (!bm) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()

                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - o + (o * .1) > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(o + (o * .1))
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()
                }
            }

            if (!bl) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()

                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - o + (o * .2) > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(o + (o * .2))
                            .toString()
                    snakes.get(position).snakePriceEdit = price.text.toString().toDouble()

                }
            }
        }

        Log.d("ITME", "onBindViewHolder: ${count.text}")

        var sd = Ticket(
            snakes[position].type.toString(),
            snakes[position].snakeCount.toString().toInt(),
            snakes[position].snakePrice.toString().toDouble()
        )
    }

    override fun getItemCount() = snakes.size

    inner class SnakeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    fun getCurrentCount(position: Int): String {
        return snakes[position].snakeCount.toString()
    }


}
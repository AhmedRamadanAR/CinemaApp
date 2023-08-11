package com.example.cinemaapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.ItemMovieBinding
import com.example.cinemaapp.databinding.SnackItemBinding
import layout.SnakeItemData

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

        if (image != null && price != null && count != null) {
            image.setImageResource(snakes[position].snakeImage)

            price.text = snakes[position].snakePrice.toString()
            count.text = snakes[position].snakeCount.toString()
        }

        if (count.text.toString().toInt() == 0) {
            price.text = 0.toString()
        }

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
            if (!bs) {
                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    price.text =
                        price.text.toString().toInt().plus(snakes[position].snakePrice)
                            .toString()
                }
            }

            else if (!bm){

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    price.text =
                        price.text.toString().toDouble().plus(snakes[position].snakePrice).plus((snakes[position].snakePrice)*.1)
                            .toString()
                }

            }

            else if (!bl){

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    price.text =
                        price.text.toString().toDouble().plus(snakes[position].snakePrice).plus((snakes[position].snakePrice)*.2)
                            .toString()
                }

            }

        }

        sub.setOnClickListener {
            if (!bs) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toInt() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toInt().minus(snakes[position].snakePrice)
                            .toString()
                }
            }

            if (!bm) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(snakes[position].snakePrice).minus((snakes[position].snakePrice)*.1)
                            .toString()
                }
            }

            if (!bl) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(snakes[position].snakePrice).minus((snakes[position].snakePrice)*.2)
                            .toString()
                }
            }



        }

//        if(!bs || !bm ||!bl){



    }

    override fun getItemCount() = snakes.size

    inner class SnakeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

}
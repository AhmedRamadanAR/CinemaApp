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
import com.example.cinemaapp.model.SharedViewModel
import com.example.cinemaapp.model.SnakeItemData
import com.example.cinemaapp.model.Ticket

class SnacksAdapter(private val snakes: List<SnakeItemData>) :
    RecyclerView.Adapter<SnacksAdapter.SnakeItemViewHolder>() {

    var x = 0
//    private val viewModel: SharedViewModel by activityViewModels()


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

//        if (yesno!=null){

//        yesno.findViewById<Button>(R.id.btn_yes).setOnClickListener {
//            if (by) {
//
//                yesno.findViewById<Button>(R.id.btn_yes)
//                    .setBackgroundResource(R.drawable.rectangle_blue)
//                yesno.findViewById<Button>(R.id.btn_no)
//                    .setBackgroundResource(R.drawable.rectangle_blue_border)
//
//                by = false
//                bn = true
//
//            } else {
//                yesno.findViewById<Button>(R.id.btn_yes)
//                    .setBackgroundResource(R.drawable.rectangle_blue_border)
//                by = true
//            }
//        }
//            yesno.findViewById<Button>(R.id.btn_no).setOnClickListener {
//
//                if (bn) {
//
//                    yesno.findViewById<Button>(R.id.btn_no)
//                        .setBackgroundResource(R.drawable.rectangle_blue)
//
//                    yesno.findViewById<Button>(R.id.btn_yes)
//                        .setBackgroundResource(R.drawable.rectangle_blue_border)
//
//                    bn = false
//                    by = true
//
//                } else {
//                    yesno.findViewById<Button>(R.id.btn_yes)
//                        .setBackgroundResource(R.drawable.rectangle_blue_border)
//                    bn = true
//                }
//            }
//            if (!by) {
//                holder.itemView.findViewById<RecyclerView>(R.id.rv_snack1).visibility = View.VISIBLE
//            }
//            if (!bn) {
//                holder.itemView.findViewById<RecyclerView>(R.id.rv_snack1).visibility = View.GONE
//            }


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
                    snakes.get(position).snakeCount = count.text.toString().toInt()
                    Log.d("ITME", "onBindViewHolder: ${count.text}")
                    var o = snakes[position].snakePrice
                    x+=35
                    price.text = x.toString()
//                        price.text.toString().toDouble().plus(o)
//                            .toString()
//                    snakes.get(position).snakePrice=price.text.toString().toDouble()

                }
            } else if (!bm) {
                x=0

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()
                    x+=35

                    price.text = x.toString()
//                        price.text.toString().toDouble().plus(snakes[position].snakePrice)
//                            .plus((snakes[position].snakePrice) * .1)
//                            .toString()
//                    snakes.get(position).snakePrice = price.text.toString().toDouble()

                }

            } else if (!bl) {

                if (count.text.toString().toInt() < 10) {
                    count.text = count.text.toString().toInt().inc().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()

                    price.text =
                        price.text.toString().toDouble().plus(snakes[position].snakePrice)
                            .plus((snakes[position].snakePrice) * .2)
                            .toString()
                }

            }

        }

        sub.setOnClickListener {
            if (!bs) {
                if (count.text.toString().toInt() > 0) {
                    count.text = count.text.toString().toInt().dec().toString()
                    snakes.get(position).snakeCount = count.text.toString().toInt()
                    Log.d("ITME", "onBindViewHolder: ${snakes.get(position).snakeCount}")
                    if (count.text.toString().toInt() == 0) {
                        price.text = 0.toString()
                    }
                }
                if (price.text.toString().toDouble() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(snakes[position].snakePrice)
                            .toString()
//                    snakes.get(position).snakePrice=price.text.toString().toDouble()

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
                if (price.text.toString().toDouble() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(snakes[position].snakePrice)
                            .minus((snakes[position].snakePrice) * .1)
                            .toString()
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
                if (price.text.toString().toDouble() - snakes[position].snakePrice > 0) {
                    price.text =
                        price.text.toString().toDouble().minus(snakes[position].snakePrice)
                            .minus((snakes[position].snakePrice) * .2)
                            .toString()
                }
            }


        }

        Log.d("ITME", "onBindViewHolder: ${count.text}")
//        snakes.get(position).snakeCount=count.text.toString().toInt()


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
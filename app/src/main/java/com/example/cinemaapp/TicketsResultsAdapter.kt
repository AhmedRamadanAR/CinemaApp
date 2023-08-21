package com.example.cinemaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.TicketItemBinding
import com.example.cinemaapp.model.FinalTickets
import com.example.cinemaapp.model.TicketsInfo
import com.squareup.picasso.Picasso
class TicketsResultsAdapter(var lista:ArrayList<FinalTickets>): RecyclerView.Adapter<TicketsResultsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView:TicketItemBinding):RecyclerView.ViewHolder(itemView.root){
        val title=itemView.tvTitle
        val id=itemView.tvId
        val money=itemView.tvMoney
        val ticketsNumber=itemView.tvTicketsNumbers
        val time=itemView.tvTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =  ViewHolder(
        (
                TicketItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                )
    )

    override fun getItemCount()=lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket = lista[position]

        holder.title.text = ticket.title
        holder.id.text = ticket.id
        holder.money.text = ticket.money
        holder.ticketsNumber.text=ticket.ticketsNumbers
        holder.time.text=ticket.time
    }
}
package com.example.cinemaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.ItemMovieBinding
import com.example.example.Results
import com.squareup.picasso.Picasso


class MoviesAdapter(private  val lista: ArrayList<Results>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
     private var listener: OnMovieClicked? = null


    interface OnMovieClicked{
     fun onClicked(position: Int)

    }

    fun setOnItemClickListener(listener:OnMovieClicked){
        this.listener=listener
    }
    inner class ViewHolder(itemView: ItemMovieBinding) : RecyclerView.ViewHolder(itemView.root) {
        val img = itemView.imageView
        fun bind(Result:Results){
            listener?.onClicked(position = adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            (
                ItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context)
                    , parent
                    , false
                )
                    )
        )

    override fun getItemCount() = lista.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = lista[position]

        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Picasso.get().load(posterUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)
        holder.itemView.setOnClickListener {
            holder.bind(lista[position])

        }
    }
}
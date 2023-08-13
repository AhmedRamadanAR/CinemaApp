package com.example.cinemaapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.ItemMovieBinding
import com.example.cinemaapp.databinding.MovieItemFavouriteBinding
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.MovieDao
import com.example.example.Results
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MoviesAdapter(private val lista: ArrayList<Results>, private val dao: MovieDao) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var listener: OnMovieClicked? = null

    interface OnMovieClicked {
        fun onClicked(position: Int)
        fun onFavClick(position: Int)
        fun onDeleteClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnMovieClicked) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: ItemMovieBinding) : RecyclerView.ViewHolder(itemView.root) {
        val img = itemView.imageView
        val fav = itemView.ivFavourite
        fun bind(Result: Results) {
            listener?.onClicked(position = adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            (
                    ItemMovieBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                    )
        )

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = lista[position]

        val posterUrl = "https://image.tmdb.org/t/p/w500/${movie.posterPath}"
        Picasso.get().load(posterUrl)
            .placeholder(R.drawable.loading)
            .into(holder.img)
        holder.itemView.setOnClickListener {
            holder.bind(lista[position])
        }
        holder.fav.setOnClickListener {
            movie.isButtonClicked = if (movie.isButtonClicked == null) true else !movie.isButtonClicked!!
            notifyItemChanged(position)
            if (movie.isButtonClicked == true) {
                listener?.onFavClick(position)
                GlobalScope.launch(Dispatchers.IO) {
                    dao.addMovie(
                        Movie(
                            posterPath = "https://image.tmdb.org/t/p/w500/${movie.posterPath}",
                            title = movie.title.toString(),
                            overView = movie.overview.toString(),
                            isFavorite = true
                        )
                    )
                }
            } else {
                listener?.onDeleteClick(position)
                GlobalScope.launch(Dispatchers.IO) {
                    dao.deleteFavoriteMovie("https://image.tmdb.org/t/p/w500/${movie.posterPath}")
                }
            }
        }

        if (movie.isButtonClicked == true) {
            holder.fav.setImageResource(R.drawable.ic_favorite_red)
        } else {
            holder.fav.setImageResource(R.drawable.ic_favorite_border_white)
        }
    }
}

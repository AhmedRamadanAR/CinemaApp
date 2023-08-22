package com.example.cinemaapppackage


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.R
import com.example.cinemaapp.databinding.MovieItemFavouriteBinding
import com.example.cinemaapp.model.Movie
import com.squareup.picasso.Picasso

class FavoriteAdapter(private  var lista: ArrayList<Movie>):RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {


    inner class ViewHolder(itemView:MovieItemFavouriteBinding):RecyclerView.ViewHolder(itemView.root)
    {
        val img=itemView.image2
        val title=itemView.tvTitle
        val overView=itemView.tvOverView

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder =
        ViewHolder(
            (
                    MovieItemFavouriteBinding.inflate(
                        LayoutInflater.from(parent.context)
                        , parent
                      , false
                    )
                    )
        )
    override fun getItemCount(): Int=lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = lista[position]
          holder.title.text=movie.title
        holder.overView.text=movie.overView
        val posterUrl = "https:image.tmdb.org/t/p/w500/${movie.posterPath}"
        Picasso.get().load(posterUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)    }
}
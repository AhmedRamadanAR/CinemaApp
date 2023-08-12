package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cinemaapp.databinding.FragmentFavoriteBinding
import com.example.example.Results

class FavoriteFragment : Fragment() {

    private lateinit var binding:FragmentFavoriteBinding

    private lateinit var favouriteMovieViewModel:MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteMovieViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }



    fun setUpRecycler() {
        // Observe the moviesLiveData object and set the adapter and layout manager for the RecyclerView
        favouriteMovieViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movie ->
            val adapter = MoviesAdapter(movie as ArrayList<Results>)
            binding.rvFavourite.adapter = adapter
            binding.rvFavourite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
//            adapter.setOnItemClickListener(object : MoviesAdapter.OnMovieClicked {

//                override fun onClicked(position: Int) {
//                    val info = bundleOf(
//                        "title" to movie.get(position).title,
//                        "photo" to movie.get(position).backdropPath,
//                        "overView" to movie.get(position).overview,
//                        "type" to movie.get(position).adult,
//                        "genre" to movie.get(position).genreIds[0],
//                        "language" to movie.get(position).originalLanguage,
//                        "rate" to movie.get(position).voteAverage
//                    )
//                    Log.d("taa", "onClicked: ${movie.get(position).title.toString()}")
//
//                }

//            })
        })

        favouriteMovieViewModel.getNowPlayingMovies()
    }









}
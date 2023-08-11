package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.Data.remote.RetrofitClient
import com.example.cinemaapp.databinding.FragmentHomeBinding
import com.example.example.Pages
import com.example.example.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Initialize the ViewModel object using ViewModelProvider
        movieViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return binding.root
    }

    fun setUpRecycler() {
        // Observe the moviesLiveData object and set the adapter and layout manager for the RecyclerView
        movieViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movie ->
            val adapter = MoviesAdapter(movie as ArrayList<Results>)
            binding.rvHome.adapter = adapter
            binding.rvHome.layoutManager = GridLayoutManager(context, 2)
            adapter.setOnItemClickListener(object : MoviesAdapter.OnMovieClicked {

                override fun onClicked(position: Int) {
                    val info = bundleOf(
                        "title" to movie.get(position).title,
                        "photo" to movie.get(position).backdropPath,
                        "overView" to movie.get(position).overview,
                        "type" to movie.get(position).adult,
                        "genre" to movie.get(position).genreIds[0],
                        "language" to movie.get(position).originalLanguage,
                        "rate" to movie.get(position).voteAverage

                    )
                    findNavController().navigate(R.id.action_basicFragment_to_movieDetailsFragment,info)
                    Log.d("taa", "onClicked: ${movie.get(position).title.toString()}")

                }

            })
        })

        movieViewModel.getNowPlayingMovies()
    }
}
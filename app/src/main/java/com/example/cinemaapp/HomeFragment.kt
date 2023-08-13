package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.databinding.FragmentHomeBinding
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.MovieDao
import com.example.cinemaapp.model.MovieDatabase
import com.example.example.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import java.text.FieldPosition

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieViewModel: MainViewModel
    private lateinit var favViewModel: FavViewModel
    private var dao: MovieDao? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            val db = MovieDatabase.buildMovieDb(requireContext())
            dao = db?.movieDao()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)
        observeViewModel()
        setUpRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)


        // Initialize the ViewModel object using ViewModelProvider
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
                    findNavController().navigate(
                        R.id.action_basicFragment_to_movieDetailsFragment,
                        info
                    )
                    Log.d("taa", "onClicked: ${movie.get(position).title.toString()}")

                }

                override fun onFavClick(position: Int) {
                    if (dao != null)
                        favViewModel.insertMovie(
                            dao!!,
                            Movie("https://image.tmdb.org/t/p/w500/${movie[position].posterPath}",movie[position].title.toString(),movie[position].overview.toString())
                        )

                }

                override fun onDeleteClick(position: Int) {
                    GlobalScope.launch(Dispatchers.IO) {
                        dao?.deleteFavoriteMovie("https://image.tmdb.org/t/p/w500/${movie[position].posterPath}")
                    }
                }

            })
        })

        movieViewModel.getNowPlayingMovies()
    }

    private fun observeViewModel() {
        favViewModel.insertLiveData.observe(viewLifecycleOwner, Observer { movie ->
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()

        })
    }
}
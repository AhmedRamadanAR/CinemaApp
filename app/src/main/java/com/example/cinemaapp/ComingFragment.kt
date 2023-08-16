package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.Data.remote.RetrofitClient
import com.example.cinemaapp.databinding.FragmentComingBinding
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.MovieDao
import com.example.cinemaapp.model.MovieDatabase
import com.example.example.Pages
import com.example.example.Results
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class ComingFragment : Fragment() {
    private lateinit var movieViewModel: MainViewModel
    private lateinit var favViewModel: FavViewModel
    private var dao: MovieDao? = null


    lateinit var binding:FragmentComingBinding
    val egyptZoneId = ZoneId.of("Africa/Cairo")
    val egyptDate = LocalDate.now(egyptZoneId)


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
        movieViewModel.getComingPlayingMovies()

        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)
        observeViewModel()
        setUpRecycler()    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentComingBinding.inflate(inflater,container,false)
        return  binding.root
    }
    fun setUpRecycler(){

        movieViewModel.moviesComingLiveData.observe(viewLifecycleOwner, Observer {movie->
            val filteredMovies = filterMoviesByDate(movie)
           val adapter= dao?.let { MoviesAdapter(filteredMovies as ArrayList<Results>, it) }
            binding.rvComing.adapter=adapter
               binding.rvComing.layoutManager= GridLayoutManager(context,2)
            if (adapter != null) {
                adapter.setOnItemClickListener(object : MoviesAdapter.OnMovieClicked {
                    override fun onClicked(position: Int) {
                    }

                    override fun onFavClick(position: Int) {
                        if (dao != null)
                            favViewModel.insertMovie(
                                dao!!,
                                Movie("https://image.tmdb.org/t/p/w500/${filteredMovies[position].posterPath}",filteredMovies[position].title.toString(),filteredMovies[position].overview.toString(), isFavorite = true)
                            )

                    }

                    override fun onDeleteClick(position: Int) {
                        GlobalScope.launch(Dispatchers.IO) {
                            dao?.deleteFavoriteMovie("https://image.tmdb.org/t/p/w500/${filteredMovies[position].posterPath}")
                        }
                    }


                })
                GlobalScope.launch(Dispatchers.IO) {
                    val favoriteMovies = dao?.readAllData()
                    if (favoriteMovies != null) {
                        for (favoriteMovie in favoriteMovies) {
                            val movieList = movieViewModel.moviesComingLiveData.value

                            val index = movieList?.indexOfFirst { it.title == favoriteMovie.title }
                            if (index != null) {
                                if (index >= 0) {
                                    movie[index].isButtonClicked = true

                                }
                            }

                        }
                    }
                    withContext(Dispatchers.Main) {
                        adapter.notifyDataSetChanged()
                    }
                }
            }
            })



    }

    fun filterMoviesByDate(movies: List<Results>): List<Results> {
        val filteredMovies = movies.filter { movie ->
            val movieDate = LocalDate.parse(movie.releaseDate)
            movieDate > egyptDate
        }
        return filteredMovies
    }
    private fun observeViewModel() {
        favViewModel.insertLiveData.observe(viewLifecycleOwner, Observer { movie ->
            Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()

        })
    }


}
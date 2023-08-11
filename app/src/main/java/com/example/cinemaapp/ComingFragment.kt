package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.Data.remote.RetrofitClient
import com.example.cinemaapp.databinding.FragmentComingBinding
import com.example.example.Pages
import com.example.example.Results
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


    lateinit var binding:FragmentComingBinding
    val egyptZoneId = ZoneId.of("Africa/Cairo")
    val egyptDate = LocalDate.now(egyptZoneId)


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
        binding= FragmentComingBinding.inflate(inflater,container,false)

        return  binding.root
    }
    fun setUpRecycler(){

        movieViewModel.moviesComingLiveData.observe(viewLifecycleOwner, Observer {movie->
            val filteredMovies = filterMoviesByDate(movie)
           val adapter=MoviesAdapter(filteredMovies as ArrayList<Results>)
            binding.rvComing.adapter=adapter
               binding.rvComing.layoutManager= GridLayoutManager(context,2)
        })

        movieViewModel.getComingPlayingMovies()


    }
    fun filterMoviesByDate(movies: List<Results>): List<Results> {
        val filteredMovies = movies.filter { movie ->
            val movieDate = LocalDate.parse(movie.releaseDate)
            movieDate > egyptDate
        }
        return filteredMovies
    }


}
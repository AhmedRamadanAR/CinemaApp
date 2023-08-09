package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


        val movieService = RetrofitClient.apiServiceInstanceComing().getComingPlayingMovies("a955e58bd823d85f421b6e04ba7fc8e0")
        movieService?.enqueue(object : Callback<Pages?>{

            override fun onResponse(call: Call<Pages?>, response: Response<Pages?>) {
                val movies = response.body()?.results ?: emptyList()
                val filteredMovies = filterMoviesByDate(movies)
                val adapter=MoviesAdapter(filteredMovies as ArrayList<Results>)
                binding.rvComing.adapter=adapter
                binding.rvComing.layoutManager= GridLayoutManager(context,2)
            }

            override fun onFailure(call: Call<Pages?>, t: Throwable) {
                t.toString()
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


}
package com.example.cinemaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cinemaapp.Data.remote.RetrofitClient
import com.example.cinemaapp.databinding.FragmentHomeBinding
import com.example.example.Pages
import com.example.example.Results
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
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

        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    fun setUpRecycler(){
        val movieService = RetrofitClient.apiServiceInstance().getNowPlayingMovies("a955e58bd823d85f421b6e04ba7fc8e0")
        movieService?.enqueue(object : Callback<Pages?>{


            override fun onResponse(call: Call<Pages?>, response: Response<Pages?>) {
                val movie=response.body()?.results ?: emptyList()
                val adapter=MoviesAdapter(movie as ArrayList<Results>)
                binding.rvHome.adapter=adapter
                binding.rvHome.layoutManager= GridLayoutManager(context,2)
            }

            override fun onFailure(call: Call<Pages?>, t: Throwable) {
                t.toString()
            }

        })


    }

}
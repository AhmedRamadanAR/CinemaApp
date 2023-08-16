package com.example.cinemaapp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cinemaapp.Data.remote.RetrofitClient

import com.example.example.Pages
import com.example.example.Results

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    val moviesLiveData = MutableLiveData<List<Results>>()
    val moviesComingLiveData=MutableLiveData<List<Results>>()
        fun getNowPlayingMovies() {
            val movieService = RetrofitClient.apiServiceInstance()
                .getNowPlayingMovies("a955e58bd823d85f421b6e04ba7fc8e0")
            movieService?.enqueue(object : Callback<Pages?> {

                override fun onResponse(call: Call<Pages?>, response: Response<Pages?>) {
                    val movie = response.body()?.results ?: emptyList()
                    moviesLiveData.value = movie
                }

                override fun onFailure(call: Call<Pages?>, t: Throwable) {
                    t.toString()
                }
            })
        }

        fun getComingPlayingMovies() {
            val moviesServiece = RetrofitClient.apiServiceInstanceComing()
                .getComingPlayingMovies("a955e58bd823d85f421b6e04ba7fc8e0")
            moviesServiece?.enqueue(object : Callback<Pages?> {
                override fun onResponse(call: Call<Pages?>, response: Response<Pages?>) {
                    val movie = response.body()?.results ?: emptyList()
                    moviesComingLiveData.value = movie

                }

                override fun onFailure(call: Call<Pages?>, t: Throwable) {
                    t.toString()
                }
            })
        }
    }

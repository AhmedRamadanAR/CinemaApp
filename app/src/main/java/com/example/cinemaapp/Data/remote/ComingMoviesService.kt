package com.example.cinemaapp.Data.remote

import com.example.example.Pages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ComingMoviesService {
    @GET("movie/upcoming")
    fun getComingPlayingMovies(@Query("api_key") apiKey: String?): Call<Pages?>?
}
package com.example.cinemaapp.Data.remote

import com.example.example.Pages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") apiKey: String?): Call<Pages?>?
}

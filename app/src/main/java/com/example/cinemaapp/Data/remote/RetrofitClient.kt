package com.example.cinemaapp.Data.remote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun instant(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun apiServiceInstance(): MovieService = instant().create((MovieService::class.java))
    fun apiServiceInstanceComing(): ComingMoviesService = instant().create((ComingMoviesService::class.java))

}


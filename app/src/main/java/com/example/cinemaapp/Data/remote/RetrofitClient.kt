package com.example.cinemaapp.Data.remote
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    fun instant(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun apiServiceInstance(): MovieService = instant().create((MovieService::class.java))
}


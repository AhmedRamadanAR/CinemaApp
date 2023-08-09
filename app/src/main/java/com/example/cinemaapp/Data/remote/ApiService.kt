package com.example.cinemaapp.Data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("?api_key=a955e58bd823d85f421b6e04ba7fc8e0")
    fun fetchMovie(){

    }
    //base url : https://api.themoviedb.org/3/movie/now_playing
}
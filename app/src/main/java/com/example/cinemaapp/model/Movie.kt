package com.example.cinemaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Movie_Table")
data class Movie(
    @PrimaryKey
var posterPath : String,
var title:String,
var overView:String,
    var isFavorite: Boolean
)

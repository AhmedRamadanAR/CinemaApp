package com.example.cinemaapp.model

data class SnakeItemData(
    val type:String,
    val snakeImage: Int,
    val snakeSize: Int,
    var snakeCount: Int,
    var snakePrice: Double,
    var snakePriceEdit: Double
)
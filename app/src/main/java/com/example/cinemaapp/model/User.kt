package com.example.cinemaapp.model

data class User(
    var name:String,
    var email:String,
    var password:String,
    var phone:String,
    var gender:String,
    var money :Double=200.0
)
package com.example.cinemaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie_Ticket")
data class FinalTickets (
    @PrimaryKey
    var title:String,
    var id:String,
    var money:String,
    var ticketsNumbers:String,
    var time:String
    )

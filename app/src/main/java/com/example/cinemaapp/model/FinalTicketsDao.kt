package com.example.cinemaapp.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FinalTicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun addTicket(ticket:FinalTickets)
    @Query("SELECT * FROM movie_ticket ")
    suspend  fun readTicket (): List<FinalTickets>
}
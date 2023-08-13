package com.example.cinemaapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun addMovie(movie:Movie)
@Query("SELECT * FROM Movie_Table ")
  suspend  fun readAllData (): List<Movie>

  @Query("DELETE FROM Movie_Table WHERE posterPath = :posterPath")
    suspend fun deleteFavoriteMovie(posterPath:String)
}

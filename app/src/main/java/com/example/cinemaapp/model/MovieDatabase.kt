package com.example.cinemaapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract  class MovieDatabase:RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        var db: MovieDatabase? = null
        fun buildMovieDb(context: Context): MovieDatabase? {
            db = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "notes"
            )
                .build()
            return db!!


        }
    }
}
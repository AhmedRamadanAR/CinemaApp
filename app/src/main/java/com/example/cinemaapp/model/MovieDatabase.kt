package com.example.cinemaapp.model
import com.example.cinemaapp.model.FinalTickets
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [FinalTickets::class,Movie::class], version = 1, exportSchema = false)
abstract  class MovieDatabase:RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun FinalTicketsDao():FinalTicketsDao

    companion object {
        var db: MovieDatabase? = null
        fun buildMovieDb(context: Context): MovieDatabase? {
            db = Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movies"
            )
                .build()
            return db!!


        }
    }
}
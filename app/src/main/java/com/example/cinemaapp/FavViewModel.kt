package com.example.cinemaapp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class FavViewModel:ViewModel() {
    val favLiveData = MutableLiveData<List<Movie>>()
    val insertLiveData = MutableLiveData<Boolean>()
    fun viewfavMovie(dao: MovieDao?) {
        viewModelScope.launch(Dispatchers.IO) {
            val viewMovie = dao?.readAllData()
            withContext(Dispatchers.Main) {
                viewMovie?.let {
                    favLiveData.value = it
                }            }
        }

    }
    fun insertMovie(dao: MovieDao?, movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            dao?.addMovie(movie)
            withContext(Dispatchers.Main) {
                insertLiveData.value = true
            }

        }

    }

}
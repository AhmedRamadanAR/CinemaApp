package com.example.cinemaapp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemaapp.model.FinalTickets
import com.example.cinemaapp.model.FinalTicketsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TicketsViewModel :ViewModel(){
    val ticketsLiveData = MutableLiveData<List<FinalTickets>>()
    val insertLiveData =MutableLiveData<Boolean>()

    fun viewTickets(dao:FinalTicketsDao){
        viewModelScope.launch(Dispatchers.IO) {
            val viewMovie = dao?.readTicket()
            withContext(Dispatchers.Main) {
                viewMovie?.let {
                    ticketsLiveData.value = it
                }
            }
        }
    }

    fun insertTickets(dao: FinalTicketsDao?, ticket: FinalTickets) {
        viewModelScope.launch(Dispatchers.IO) {
            dao?.addTicket(ticket)
            withContext(Dispatchers.Main) {
               insertLiveData.value = true
            }

       }

    }
}
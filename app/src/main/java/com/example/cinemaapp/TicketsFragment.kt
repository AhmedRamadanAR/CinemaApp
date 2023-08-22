package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.databinding.FragmentHomeBinding
import com.example.cinemaapp.databinding.FragmentTicketsBinding
import com.example.cinemaapp.databinding.TicketItemBinding
import com.example.cinemaapp.model.FinalTickets
import com.example.cinemaapp.model.FinalTicketsDao
import com.example.cinemaapp.model.Movie
import com.example.cinemaapp.model.MovieDao
import com.example.cinemaapp.model.MovieDatabase
import com.example.cinemaapp.model.Ticket
import com.example.cinemaapp.model.TicketsInfo
import com.example.cinemaapppackage.FavoriteAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TicketsFragment : Fragment() {
   lateinit var binding:FragmentTicketsBinding
    private lateinit var ticketsViewModel:TicketsViewModel

    private var dao: FinalTicketsDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {

            val db = MovieDatabase.buildMovieDb(requireContext())
            dao = db?.FinalTicketsDao()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ticketsViewModel = ViewModelProvider(this).get(TicketsViewModel::class.java)

        binding = FragmentTicketsBinding.inflate(inflater, container, false)

        return binding.root
    }
    fun setUpRecycler() {
        ticketsViewModel.ticketsLiveData.observe(viewLifecycleOwner, Observer { ticket ->
            val adapter = TicketsResultsAdapter(ticket as ArrayList<FinalTickets>)

        binding.rvTickets.adapter=adapter
        binding.rvTickets.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        })

        dao?.let {ticketsViewModel.viewTickets(it) }
    }
}



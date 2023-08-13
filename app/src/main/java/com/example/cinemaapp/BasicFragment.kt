package com.example.cinemaapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentBasicBinding
import com.google.android.material.tabs.TabLayoutMediator


class BasicFragment : Fragment() {
    val homeFragment=HoldFragment()
    val ticketsFragment=TicketsFragment()
    val favoriteFragment=FavoriteFragment()
    lateinit var binding:FragmentBasicBinding
//  lateinit var  movieViewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragment(homeFragment)
//        binding.tr.setOnClickListener {
////            movieViewModel.increment()
//        }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.myHome-> {
                    setFragment(homeFragment)
                }
                R.id.myFavorite->{
                    fragmentManager?.beginTransaction()?.remove(homeFragment)?.commit()

                    setFragment(favoriteFragment)              }
                R.id.myTickets->{
                    fragmentManager?.beginTransaction()?.remove(homeFragment)?.commit()

                    setFragment(ticketsFragment)                       }

            }
            true
        }


    }
//    fun observer(){
//        movieViewModel.counterLiveData.observe(viewLifecycleOwner){
//            binding.tr.text=it.toString()
//        }
//    }

    fun setFragment(fragment:Fragment)=

        parentFragmentManager?.beginTransaction()?.apply {

            replace(R.id.fragmentContainerView2, fragment)

            commit()
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        movieViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//observer()
        // Inflate the layout for this fragment
        binding = FragmentBasicBinding.inflate(inflater,container,false)
        return binding.root    }


}

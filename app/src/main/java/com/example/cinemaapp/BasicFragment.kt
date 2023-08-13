package com.example.cinemaapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cinemaapp.databinding.FragmentBasicBinding


class BasicFragment : Fragment() {
    val homeFragment=HoldFragment()
    val ticketsFragment=TicketsFragment()
    val favoriteFragment=FavoriteFragment()
    lateinit var binding:FragmentBasicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragment(homeFragment)

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

    fun setFragment(fragment:Fragment)=

        parentFragmentManager?.beginTransaction()?.apply {

            replace(R.id.fragmentContainerView2, fragment)

            commit()
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasicBinding.inflate(inflater,container,false)
        return binding.root    }

}

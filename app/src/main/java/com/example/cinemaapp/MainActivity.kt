package com.example.cinemaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cinemaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
   private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun setUpRecycler(){
   //     var lista:List<Int> = listOf<Int>(R.drawable.movie1,R.drawable.movie1,R.drawable.movie1,R.drawable.movie1,R.drawable.movie1)
     //   val adapter=MoviesAdapter(lista)
       // binding.rv.adapter=adapter
        //binding.rv.layoutManager=GridLayoutManager(this,2)

    }
}
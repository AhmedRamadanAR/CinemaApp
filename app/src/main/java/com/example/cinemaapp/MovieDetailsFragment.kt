package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso

class MovieDetailsFragment : Fragment() {
    lateinit var binding:FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
setUpViews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
     //   findNavController().navigate(R.id.basicFragment)

    }
fun setUpViews(){

    val genre=arguments?.getInt("genre")
    val type=arguments?.getString("type")
    val photoPath= arguments?.getString("photo")

    binding.tvTitle.text=arguments?.getString("title")
    binding.tvDescription.text=arguments?.getString("overView")
    binding.tvLang.text=arguments?.getString("language")
    binding.tvRate.text= arguments?.getDouble("rate").toString()
 binding.btnSubmit.setOnClickListener{
   findNavController().navigate(R.id.action_movieDetailsFragment_to_checkOutFragment)
}
    when(genre){
        28->binding.tvGenre.text="Action"
        12->binding.tvGenre.text="Adventure"
        16->binding.tvGenre.text="Animation"
        35->binding.tvGenre.text="Comedy"
        80->binding.tvGenre.text="Crime"
        18->binding.tvGenre.text="Drama"
        878->binding.tvGenre.text="Science Fiction"
        10749->binding.tvGenre.text="Romance"
        27->binding.tvGenre.text="Horror"


    }
    if(type=="true"){
        binding.tvType.text="Adults +18"
    }
    else{
        binding.tvType.text="Family"

    }
    val posterUrl = "https://image.tmdb.org/t/p/w500/${photoPath}"
    Picasso.get().load(posterUrl)
        .placeholder(R.drawable.ic_launcher_background)
        .into(binding.imageView2)


}

}
package com.example.cinemaapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemaapp.databinding.FragmentBuyTicketSnackBinding
import com.example.cinemaapp.model.SnakeItemData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class BuyTicketSnack : Fragment() {

    private lateinit var binding: FragmentBuyTicketSnackBinding

    private lateinit var database: DatabaseReference

    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient
    var time:String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuyTicketSnackBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveMoneyFromDatabase()


        var snake = mutableListOf(
            SnakeItemData(
                "popcorn",
                R.drawable.popcorn,
                0,
                0,
                35.0,
                0.0
            ),
            SnakeItemData(
                "pepsi",
                R.drawable.can2,
                1,
                0,
                50.0,
                0.0
            ),
            SnakeItemData(
                "chips",
                R.drawable.chips,
                2,
                0,
                100.0,
                0.0
            ),

            )


        var ticket = mutableListOf(
            SnakeItemData(
                "ticket",
                R.drawable.ticket,
                0,
                0,
                100.0,
                0.0
            )
        )

        val adapterCategory = SnacksAdapter(snake)
        view.findViewById<RecyclerView>(R.id.rv_snack1).adapter = adapterCategory
        view.findViewById<RecyclerView>(R.id.rv_snack1).layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val currentCount = adapterCategory.getCurrentCount(0)


        val adapterCategoryTicket = TicketAdapter(ticket)
        view.findViewById<RecyclerView>(R.id.rv_ticket).adapter = adapterCategoryTicket
        view.findViewById<RecyclerView>(R.id.rv_ticket).layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        var by = true
        var bn = true
        binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)

        binding.btnYes.setOnClickListener {

            if (by) {

                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue_border)
                binding.rvSnack1.visibility = View.VISIBLE

                by = false
                bn = true

            }

        }

        binding.btnNo.setOnClickListener {

            if (bn) {

                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue_border)
                binding.rvSnack1.visibility = View.INVISIBLE

                bn = false
                by = true

            }

        }

        var t1 = true
        var t2 = true
        binding.btnTime1.setBackgroundResource(R.drawable.rectangle_blue)
        binding.btnTime1.setOnClickListener {

            if (t1) {
                  time=binding.btnTime1.text.toString()
                binding.btnTime1.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnTime2.setBackgroundResource(R.drawable.rectangle_blue_border)

                t1 = false
                t2 = true

            }

        }

        binding.btnTime2.setOnClickListener {

            if (t2) {
                time=binding.btnTime2.text.toString()

                binding.btnTime2.setBackgroundResource(R.drawable.rectangle_blue)
                binding.btnTime1.setBackgroundResource(R.drawable.rectangle_blue_border)

                t2 = false
                t1 = true

            }

        }



        binding.btnNext.setOnClickListener {
            val title = arguments?.getString("title")


            val info = bundleOf(
                "quantity" to snake.get(0).snakeCount.toString(),
                "type" to snake.get(0).type.toString(),
                "price" to snake.get(0).snakePriceEdit.toString(),

                "quantity1" to snake.get(1).snakeCount.toString(),
                "type1" to snake.get(1).type.toString(),
                "price1" to snake.get(1).snakePriceEdit.toString(),

                "quantity2" to snake.get(2).snakeCount.toString(),
                "type2" to snake.get(2).type.toString(),
                "price2" to snake.get(2).snakePriceEdit.toString(),
                "quantity3" to ticket.get(0).snakeCount.toString(),
                "type3" to ticket.get(0).type.toString(),
                "price3" to ticket.get(0).snakePriceEdit.toString(),
                "title" to title,
                "time" to time


                )


            if (ticket.get(0).snakeCount.toString().toInt() > 0) {

                    findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck, info)
            }

            else {
                Toast.makeText(context, "You Need To Select Ticket", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSignout.setOnClickListener {
            signOut()
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.movieDetailsFragment, true)
                .setPopUpTo(R.id.buyTicketSnack, true)
                .setPopUpTo(R.id.basicFragment, true)
                .build()
            findNavController().navigate(
                R.id.action_buyTicketSnack_to_loginFragment,
                null,
                navOptions
            )
//            findNavController().navigate(R.id.loginFragment)
        }

    }

    fun signOut() {
        auth.signOut()

        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {}

    }

    fun retrieveMoneyFromDatabase() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            database = FirebaseDatabase.getInstance().getReference("users").child(userId)

            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val money = dataSnapshot.child("money").getValue(Double::class.java)
                    if (money == null)
                        binding.tvMoneyBeforeEdit.text = 0.toString() + "$"
                    else
                        binding.tvMoneyBeforeEdit.text = money.toString() + "$"
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(
                        context,
                        "SomeThing Go wrong ${databaseError.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            database.addListenerForSingleValueEvent(valueEventListener)
        } else {
            Toast.makeText(context, "You'r Not Logged In Please Login First", Toast.LENGTH_SHORT)
                .show()
        }
    }


}


//package com.example.cinemaapp
//
//import android.content.ContentValues.TAG
//import android.os.Bundle
//import android.util.Log
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import android.widget.Toast
//import androidx.core.os.bundleOf
//import androidx.core.view.get
//import androidx.navigation.NavOptions
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.cinemaapp.databinding.FragmentBuyTicketSnackBinding
//import com.example.cinemaapp.model.SnakeItemData
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//
//
//class BuyTicketSnack : Fragment() {
//
//    private lateinit var binding: FragmentBuyTicketSnackBinding
//
//    private lateinit var database: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentBuyTicketSnackBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        retrieveMoneyFromDatabase()
//
//
//        var snake = mutableListOf(
//            SnakeItemData(
//                "popcorn",
//                R.drawable.popcorn,
//                0,
//                0,
//                35.0,
//                0.0
//            ),
//            SnakeItemData(
//                "pepsi",
//                R.drawable.can2,
//                1,
//                0,
//                50.0,
//                0.0
//            ),
//            SnakeItemData(
//                "chips",
//                R.drawable.chips,
//                2,
//                0,
//                100.0,
//                0.0
//            ),
//
//            )
//
//
//        var ticket = mutableListOf(
//            SnakeItemData(
//                "ticket",
//                R.drawable.ticket,
//                0,
//                0,
//                100.0,
//                0.0
//            )
//        )
//
//        val adapterCategory = SnacksAdapter(snake)
//        view.findViewById<RecyclerView>(R.id.rv_snack1).adapter = adapterCategory
//        view.findViewById<RecyclerView>(R.id.rv_snack1).layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//       // val currentCount = adapterCategory.getCurrentCount(0)
//
//        val adapterCategoryTicket = TicketAdapter(ticket)
//        view.findViewById<RecyclerView>(R.id.rv_ticket).adapter = adapterCategoryTicket
//        view.findViewById<RecyclerView>(R.id.rv_ticket).layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//
//
//
//
//
//        var by = true
//        var bn = true
//        binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)
//
//        binding.btnYes.setOnClickListener {
//
//            if (by) {
//
//                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue)
//                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue_border)
//                binding.rvSnack1.visibility = View.VISIBLE
//
//                by = false
//                bn = true
//
//            }
//
//        }
//
//        binding.btnNo.setOnClickListener {
//
//            if (bn) {
//
//                binding.btnNo.setBackgroundResource(R.drawable.rectangle_blue)
//                binding.btnYes.setBackgroundResource(R.drawable.rectangle_blue_border)
//                binding.rvSnack1.visibility = View.INVISIBLE
//
//                bn = false
//                by = true
//
//            }
//
//        }
//        var t1 = true
//        var t2 = true
//
//        binding.btnTime1.setOnClickListener {
//
//            if (t1) {
//
//                binding.btnTime1.setBackgroundResource(R.drawable.rectangle_blue)
//                binding.btnTime2.setBackgroundResource(R.drawable.rectangle_blue_border)
//
//                t1 = false
//                t2 = true
//
//            }
//
//        }
//
//        binding.btnTime2.setOnClickListener {
//
//            if (t2) {
//
//                binding.btnTime2.setBackgroundResource(R.drawable.rectangle_blue)
//                binding.btnTime1.setBackgroundResource(R.drawable.rectangle_blue_border)
//
//                t2 = false
//                t1 = true
//
//            }
//
//        }
//
//
//        binding.btnNext.setOnClickListener {
//
//            val info = bundleOf(
//                "quantity" to snake.get(0).snakeCount.toString(),
//                "type" to snake.get(0).type.toString(),
//                "price" to snake.get(0).snakePriceEdit.toString(),
//
//                "quantity1" to snake.get(1).snakeCount.toString(),
//                "type1" to snake.get(1).type.toString(),
//                "price1" to snake.get(1).snakePriceEdit.toString(),
//
//                "quantity2" to snake.get(2).snakeCount.toString(),
//                "type2" to snake.get(2).type.toString(),
//                "price2" to snake.get(2).snakePriceEdit.toString(),
//
//
//                "quantity3" to ticket.get(0).snakeCount.toString(),
//                "type3" to ticket.get(0).type.toString(),
//                "price3" to ticket.get(0).snakePriceEdit.toString(),
//
//
//
//                )
//
//            if (ticket.get(0).snakeCount.toString().toInt()>0&&(!t1||!t2))
//                findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck, info)
//            else{
//                Toast.makeText(context, "You Need To Select Ticket & Time", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        binding.btnSignout.setOnClickListener {
//            signOut()
//            val navOptions = NavOptions.Builder()
//                .setPopUpTo(R.id.movieDetailsFragment, true)
//                .setPopUpTo(R.id.buyTicketSnack, true)
//                .setPopUpTo(R.id.basicFragment,true)
//                .build()
//            findNavController().navigate(R.id.action_buyTicketSnack_to_loginFragment, null, navOptions)
////            findNavController().navigate(R.id.loginFragment)
//        }
//
//    }
//
//    fun signOut() {
//        FirebaseAuth.getInstance().signOut()
//    }
//
//    fun retrieveMoneyFromDatabase() {
//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        if (userId != null) {
//            database = FirebaseDatabase.getInstance().getReference("users").child(userId)
//
//            val valueEventListener = object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val money = dataSnapshot.child("money").getValue(Double::class.java)
//                    if (money == null)
//                        binding.tvMoneyBeforeEdit.text = 0.toString() + "$"
//                    else
//                        binding.tvMoneyBeforeEdit.text = money.toString() + "$"
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    Toast.makeText(
//                        context,
//                        "SomeThing Go wrong ${databaseError.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//            database.addListenerForSingleValueEvent(valueEventListener)
//        } else {
//            Toast.makeText(context, "You'r Not Logged In Please Login First", Toast.LENGTH_SHORT)
//                .show()
//        }
//    }
//
//
//}
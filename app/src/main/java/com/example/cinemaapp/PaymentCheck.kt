package com.example.cinemaapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentMovieDetailsBinding
import com.example.cinemaapp.databinding.FragmentPaymentCheckBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentCheck : Fragment() {

    private lateinit var binding: FragmentPaymentCheckBinding

    private lateinit var database: DatabaseReference

    var price = ""
    var price1 = ""
    var price2 = ""
    var price3 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        retrieveMoneyBeforeFromDatabase()

        binding.btnBuy.setOnClickListener {
            retrieveMoneyFromDatabase()
            lifecycleScope.launch {
                delay(200) // delay for 3 seconds
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.buyTicketSnack, true)
                    .build()
            findNavController().navigate(R.id.basicFragment, null, navOptions)
            }

//            findNavController().navigate(R.id.action_buyTicketSnack_to_paymentCheck)


        }

    }

    private fun setUpView() {

        val count = arguments?.getString("quantity")
        val type = arguments?.getString("type")
        price = arguments?.getString("price").toString()

        val count1 = arguments?.getString("quantity1")
        val type1 = arguments?.getString("type1")
        price1 = arguments?.getString("price1").toString()

        val count2 = arguments?.getString("quantity2")
        val type2 = arguments?.getString("type2")
        price2 = arguments?.getString("price2").toString()


        val count3 = arguments?.getString("quantity3")
        val type3 = arguments?.getString("type3")
        price3 = arguments?.getString("price3").toString()


//        Log.d("COME", "setUpView: $count")

        binding.tvPopcornCount.text = count
        binding.tvPopcornPrice.text = price + "$"

        binding.tvDrinkCount.text = count1
        binding.tvDrinkPrice.text = price1 + "$"
//
        binding.tvChipsCount.text = count2
        binding.tvChipsPrice.text = price2 + "$"

        binding.tvTicketCount.text = count3
        binding.tvTicketPrice.text = price3 + "$"

        binding.tvTotalQuantityCount.text =
            (count!!.toInt() + count1!!.toInt() + count2!!.toInt() + count3!!.toInt()).toString()

        binding.tvTotalPriceMoney.text =
            (price!!.toDouble() + price1!!.toDouble() + price2!!.toDouble() + price3!!.toDouble()).toString() + "$"

    }


    fun retrieveMoneyFromDatabase() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            database = FirebaseDatabase.getInstance().getReference("users").child(userId)
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val money = dataSnapshot.child("money").getValue(Double::class.java)
                    var v1 = 0.0
                    var v2 = 0.0
                    var v3 = 0.0
                    var v4 = 0.0
                    if (price.toString().isNotEmpty()) {
                        v1 = price.toString().toDouble()
                    }
                    if (price1.toString().isNotEmpty()) {
                        v2 = price1.toString().toDouble()
                    }
                    if (price2.toString().isNotEmpty()) {
                        v3 = price2.toString().toDouble()
                    }
                    if (price3.toString().isNotEmpty()) {
                        v4 = price3.toString().toDouble()
                    }

                    if (money != null) {
                        if (money - (v1 + v2 + v3 + v4) > 0) {
                            database.child("money").setValue(money - (v1 + v2 + v3 + v4))
                            binding.tvMoneyAfterEdit.text =
                                (money - (v1 + v2 + v3 + v4)).toString() + "$"
                        } else {
                            Toast.makeText(context, "You Haven't Enough Money", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
//                    else {
//                        Toast.makeText(context, "Null Money", Toast.LENGTH_SHORT)
//                            .show()
//                    }
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

//            findNavController().navigate(R.id.action_paymentCheck_to_basicFragment)
//        val navOptions = NavOptions.Builder()
//            .setPopUpTo(R.id.buyTicketSnack, true)
//            .build()
//        findNavController().navigate(R.id.basicFragment, null, navOptions)
    }


    fun retrieveMoneyBeforeFromDatabase() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            database = FirebaseDatabase.getInstance().getReference("users").child(userId)

            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val money = dataSnapshot.child("money").getValue(Double::class.java)
                    if (money == null)
                        binding.tvMoneyAfterEdit.text = 0.toString() + "$"
                    else
                        binding.tvMoneyAfterEdit.text = money.toString() + "$"
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

//        val navOptions = NavOptions.Builder()
//            .setPopUpTo(R.id.buyTicketSnack, true)
//            .build()
//        findNavController().navigate(R.id.basicFragment, null, navOptions)
    }
}


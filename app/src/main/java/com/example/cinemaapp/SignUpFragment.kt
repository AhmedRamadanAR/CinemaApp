package com.example.cinemaapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentSignupBinding
import java.util.*


class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignupBinding

    private lateinit var dateTextView: TextView
    private lateinit var dateButton: Button
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            Log.d("hello", "onViewCreated: ${binding.etName.text.toString()}")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        dateTextView = findViewById(R.id.date_text_view)
//        dateButton = findViewById(R.id.date_button)
//
//        val calendar = Calendar.getInstance()
//        val year = calendar.get(Calendar.YEAR)
//        val month = calendar.get(Calendar.MONTH)
//        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
//
//        dateButton.setOnClickListener {
//            val datePickerDialog = DatePickerDialog(
//                this,
//                { _, year, month, dayOfMonth ->
//                    dateTextView.text = "$dayOfMonth/${month + 1}/$year"
//                },
//                year,
//                month,
//                dayOfMonth
//            )
//            datePickerDialog.show()
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment
    }

}
package com.example.cinemaapp

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.cinemaapp.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*
import kotlin.collections.HashMap


class SignUpFragment : Fragment() {


    private lateinit var binding: FragmentSignupBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    private var selectedValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val radioGroup = binding.radioGroup
//        val selectedRadioButton = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
//        selectedValue = selectedRadioButton.text.toString()
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        progressDialog = ProgressDialog(context)
//        progressDialog.setTitle("Please Wait")
//        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnSignup.setOnClickListener {
            Log.d("TAG10", "onViewCreated: ${binding.tvUserName.text.toString()}")
            Toast.makeText(requireContext(),"Hello",Toast.LENGTH_SHORT).show()
            validateData()
        }


    }

    private var name = ""
    private var email = ""
    private var password = ""
    private var phone = ""
    private var gender = ""

    private fun validateData() {


        name = binding.tvUserName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        phone = binding.etPhone.text.toString().trim()
        gender = selectedValue
        val cPass = binding.etPasswordConf.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(requireContext(), "Enter Your Name", Toast.LENGTH_SHORT).show()
        }

        if (phone.isEmpty()) {
            Toast.makeText(activity, "Enter Your Phone", Toast.LENGTH_SHORT).show()
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(context, "Enter Your Email", Toast.LENGTH_SHORT).show()
        }

        if (password.isEmpty()) {
            Toast.makeText(context, "Enter Your Password", Toast.LENGTH_SHORT).show()
        }

        if (cPass.isEmpty()) {
            Toast.makeText(context, "Enter Your Password", Toast.LENGTH_SHORT).show()
        }

        if (password != cPass) {
            Toast.makeText(context, "Enter Your Password", Toast.LENGTH_SHORT).show()
        }

        Log.d(TAG, "validateData: ${gender}")
//        else {
//            createUserAccount()
//        }


    }

//    private fun createUserAccount() {
//
//        progressDialog.setMessage("Creating Account")
//        progressDialog.show()
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//            .addOnSuccessListener {
//
//                updateUserInfo()
//
//            }
//            .addOnFailureListener {e->
//                progressDialog.dismiss()
//                Toast.makeText(context, "Failed Create Account due to ${e.message}", Toast.LENGTH_SHORT).show()
//
//            }
//
//
//    }
//
//    private fun updateUserInfo() {
//
//        progressDialog.setMessage("Saving User Info")
//
//        val timeStamp = System.currentTimeMillis()
//
//        val uid = firebaseAuth.uid
//
//        val hashMap: HashMap<String,Any?> = HashMap()
//        hashMap["uid"] = uid
//        hashMap["email"] = email
//        hashMap["name"] = name
//        hashMap["phone"] = phone
//        hashMap["gender"] = gender
//        hashMap["profileImage"] = ""
//        hashMap["userType"] = "user"
//        hashMap["timeStamp"] = timeStamp
//
//        val ref = FirebaseDatabase.getInstance().getReference()
//
//        ref.child(uid!!)
//            .setValue(hashMap)
//            .addOnSuccessListener {e->
//                progressDialog.dismiss()
//                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
//                startActivity(Intent(context,MainActivity::class.java))
////                finish()
//
//            }
//            .addOnFailureListener {e->
//                progressDialog.dismiss()
//                Toast.makeText(context, "Failed Saving User Info due to ${e.message}", Toast.LENGTH_SHORT).show()
//
//            }
//
//    }


}
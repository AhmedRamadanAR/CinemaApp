package com.example.cinemaapp

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.cinemaapp.databinding.FragmentSignupBinding
import com.example.cinemaapp.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    private lateinit var database: FirebaseDatabase

    private lateinit var userRef: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth = Firebase.auth
        database = Firebase.database
        userRef = database.getReference("users")

        progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.btnSignup.setOnClickListener {
            validateData()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    val phoneNumberregex = "^01[0|1|2|5]\\d{8}$".toRegex()
    val Emailregex = "^[a-zA-Z]{4,}.*@.*\\.[a-zA-Z]+".toRegex()
    val fullnameregex = "^[a-zA-Z]{4,} [a-zA-Z]{4,}".toRegex()
    val passwordregex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$".toRegex()

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumberregex.matches(phoneNumber)
    }

    fun validateEmail(email: String): Boolean {
        return Emailregex.matches(email)
    }


    fun validateFullName(fullName: String): Boolean {
        return fullnameregex.matches(fullName)
    }

    fun validatePassword(password: String): Boolean {
        return passwordregex.matches(password)
    }


    private var name = ""
    private var email = ""
    private var password = ""
    private var phone = ""
    private var gender = ""
    private var money = 200.0
    private fun validateData() {

        name = binding.etName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        phone = binding.etPhone.text.toString().trim()
        val cPass = binding.etPasswordConf.text.toString().trim()

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() ||
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            || cPass.isEmpty() || password != cPass || gender == ""
        ) {
            if (name.isEmpty()) {
                Toast.makeText(activity, "Enter Your Name", Toast.LENGTH_SHORT).show()
            } else if (!validateFullName(name)) {
                Toast.makeText(context, "Name not valid", Toast.LENGTH_SHORT).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(activity, "Enter Your Phone", Toast.LENGTH_SHORT).show()
            } else if (!validatePhoneNumber(phone)) {
                Toast.makeText(context, "Phone number not valid", Toast.LENGTH_SHORT).show()
            }

            else if (!validateEmail(email)) {
                Toast.makeText(context, "Email not valid", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(context, "Enter Your Password", Toast.LENGTH_SHORT).show()
            } else if (cPass.isEmpty()) {
                Toast.makeText(context, "Enter Your Conf Password", Toast.LENGTH_SHORT).show()
            } else if (!validatePassword(password)) {
                Toast.makeText(
                    context,
                    "Password not valid must has at least one special character,uppercase,digit, and a length of at least 8 characters",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (password != cPass) {
                Toast.makeText(context, "Password Not Same", Toast.LENGTH_SHORT).show()
            } else if (binding.rbMale.isChecked) {
                gender = "Male"
            } else if (binding.rbFemale.isChecked) {
                gender = "Female"
            } else if (gender.isEmpty()) {
                Toast.makeText(context, "Select Gender", Toast.LENGTH_SHORT).show()
            }
        } else {
            createUserAccount()
        }
    }

    private fun createUserAccount() {

        progressDialog.setMessage("Creating Account")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = firebaseAuth.currentUser
                saveUserData(user, name.capitalize(), email, password, phone, gender, money)
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(
                    context,
                    "Failed Create Account due to ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }


    private fun saveUserData(
        user: FirebaseUser?,
        name: String,
        email: String,
        password: String,
        phone: String,
        gender: String,
        money: Double
    ) {
        if (user != null) {
            val userData = User(
                name = name,
                email = email,
                password = password,
                phone = phone,
                gender = gender,
                money = money
            )

            userRef.child(user.uid).setValue(userData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "User data saved.", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)

                    clearFields()

                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(), "User data save failed.", Toast.LENGTH_SHORT)
                        .show()
                }
        } else {
            Toast.makeText(requireContext(), "User is null.", Toast.LENGTH_SHORT).show()
        }

        progressDialog.dismiss()
    }

    private fun clearFields() {
        name = ""
        email = ""
        password = ""
        phone = ""
        gender = ""

        binding.etName.setText("")
        binding.etPhone.setText("")
        binding.etEmail.setText("")
        binding.etPassword.setText("")
        binding.etPasswordConf.setText("")

    }

}
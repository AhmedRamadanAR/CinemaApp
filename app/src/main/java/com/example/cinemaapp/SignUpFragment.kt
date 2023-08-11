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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    private var selectedValue = ""


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

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


    private var name = ""
    private var email = ""
    private var password = ""
    private var phone = ""
    private var gender = ""

    private fun validateData() {

        name = binding.etName.text.toString().trim()
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()
        phone = binding.etPhone.text.toString().trim()
        val cPass = binding.etPasswordConf.text.toString().trim()

        if (name.isEmpty() || phone.isEmpty() || password.isEmpty() ||
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            || cPass.isEmpty() || password != cPass
        ) {
            if (name.isEmpty()) {
                Toast.makeText(activity, "Enter Your Name", Toast.LENGTH_SHORT).show()
            } else if (phone.isEmpty()) {
                Toast.makeText(activity, "Enter Your Phone", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(context, "Enter Your Email", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(context, "Enter Your Password", Toast.LENGTH_SHORT).show()
            } else if (cPass.isEmpty()) {
                Toast.makeText(context, "Enter Your Conf Password", Toast.LENGTH_SHORT).show()
            } else if (password != cPass) {
                Toast.makeText(context, "Password Not Same", Toast.LENGTH_SHORT).show()
            } else if (binding.radioGroup.checkedRadioButtonId == 2131231140) {
                gender = "Male"
            } else if (binding.radioGroup.checkedRadioButtonId == 2131231139) {
                gender = "Female"
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
                updateUserInfo()
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

    private fun updateUserInfo() {

        progressDialog.setMessage("Saving User Info")

        val timeStamp = System.currentTimeMillis()

        val uid = firebaseAuth.uid

        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["phone"] = phone
        hashMap["gender"] = gender
        hashMap["password"] = password
        hashMap["profileImage"] = ""
        hashMap["userType"] = "user"
        hashMap["timeStamp"] = timeStamp

        val ref = FirebaseDatabase.getInstance().getReference()

        progressDialog.dismiss()

        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(
                    context,
                    "Failed Saving User Info due to ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
    }
}
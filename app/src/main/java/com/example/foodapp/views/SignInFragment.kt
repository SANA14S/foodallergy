package com.example.foodapp.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.foodapp.R
import com.example.foodapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory



class SignInFragment : Fragment() {
    private var emailEdit: EditText? = null
    private var passEdit: EditText? = null
    private var signUpText: TextView? = null
    private var signInBtn: Button? = null
    private var viewModel: AuthViewModel? = null
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            AndroidViewModelFactory.getInstance(activity!!.application)
        ).get<AuthViewModel>(
            AuthViewModel::class.java
        )
        viewModel!!.userData.observe(this, object : Observer<FirebaseUser?> {
            override fun onChanged(firebaseUser: FirebaseUser) {
                if (firebaseUser != null) {
                    navController.navigate(R.id.action_signInFragment_to_signOutFragment)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEdit = view.findViewById<EditText>(R.id.emailEditSignIn)
        passEdit = view.findViewById<EditText>(R.id.passEditSignIn)
        signUpText = view.findViewById<TextView>(R.id.signUpText)
        signInBtn = view.findViewById<Button>(R.id.signInBtn)
        navController = findNavController(view)
        signUpText.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_signInFragment_to_signUpFragment) })
        signInBtn.setOnClickListener(View.OnClickListener {
            val email = emailEdit.getText().toString()
            val pass = passEdit.getText().toString()
            if (!email.isEmpty() && !pass.isEmpty()) {
                viewModel!!.signIn(email, pass)
            }
        })
    }
}
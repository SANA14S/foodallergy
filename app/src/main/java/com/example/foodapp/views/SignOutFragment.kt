package com.example.foodapp.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.example.foodapp.R
import com.example.foodapp.viewmodel.AuthViewModel


class SignOutFragment : Fragment() {
    private var viewModel: AuthViewModel? = null
    private var navController: NavController? = null
    private var signOutBtn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get<AuthViewModel>(
            AuthViewModel::class.java
        )
        viewModel!!.loggedStatus.observe(this, object : Observer<Boolean?> {
            override fun onChanged(aBoolean: Boolean) {
                if (aBoolean) {
                    navController.navigate(R.id.action_signOutFragment_to_signInFragment)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController(view)
        signOutBtn = view.findViewById<Button>(R.id.signOutBtn)
        signOutBtn.setOnClickListener(View.OnClickListener { viewModel!!.signOut() })
    }
}

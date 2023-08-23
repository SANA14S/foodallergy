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
import com.example.foodapp.R
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.example.foodapp.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseUser
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory


class SignUpFragment : Fragment() {
    private var emailEdit: EditText? = null
    private var passEdit: EditText? = null
    private var signInText: TextView? = null
    private var signUpBtn: Button? = null
    private var viewModel: AuthViewModel? = null
    private var navController: NavController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get<AuthViewModel>(
            AuthViewModel::class.java
        )
        viewModel!!.userData.observe(this, object : Observer<FirebaseUser?> {
            override fun onChanged(firebaseUser: FirebaseUser) {
                if (firebaseUser != null) {
                    navController.navigate(R.id.action_signUpFragment_to_signInFragment)
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailEdit = view.findViewById<EditText>(R.id.emailEditSignUp)
        passEdit = view.findViewById<EditText>(R.id.passEditSignUp)
        signInText = view.findViewById<TextView>(R.id.signInText)
        signUpBtn = view.findViewById<Button>(R.id.signUpBtn)
        navController = findNavController(view)
        signInText.setOnClickListener(View.OnClickListener { navController.navigate(R.id.action_signUpFragment_to_signInFragment) })
       signUpBtn.setOnClickListener(View.OnClickListener {
            val email = emailEdit.getText().toString()
            val pass = passEdit.getText().toString()
            if (!email.isEmpty() && !pass.isEmpty()) {
                viewModel!!.register(email, pass)
            }
        })
    }
}

}

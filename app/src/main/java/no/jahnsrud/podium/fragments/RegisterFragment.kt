package no.jahnsrud.podium.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.emailField
import kotlinx.android.synthetic.main.fragment_register.passwordField

import no.jahnsrud.podium.R
import no.jahnsrud.podium.logic.UserHelper

class RegisterFragment : Fragment() {

    var mAuth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onStart() {
        super.onStart()

        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        register_registerButton.setOnClickListener({
            register()
        })

        register_openLoginButton.setOnClickListener({
            openLogin()
        })

        register_cancelButton.setOnClickListener({
            closeLogin()
        })
    }

    fun register() {
        val email:String = emailField.text.toString()
        val password:String = passwordField.text.toString()

        if (UserHelper().isInputValid (email, password)) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context as Activity, OnCompleteListener<AuthResult> { task ->

                if (task.isSuccessful) {
                    print("Logged in!")
                    closeLogin()

                } else {
                    print("Logged in!")
                }

            })


        }
    }

    fun openLogin() {

        val fragment: Fragment = LoginFragment()

        val transaction = fragmentManager?.beginTransaction()
        if (transaction != null) {
            transaction.replace(no.jahnsrud.podium.R.id.userLoginFragment, fragment)
            transaction.addToBackStack("transaction_name")
            transaction.commit()
        }
    }

    fun closeLogin() {
        this.activity?.finish()
    }

}

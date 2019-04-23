package no.jahnsrud.podium.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.activity_login.*
import no.jahnsrud.podium.UserHelper


class LoginFragment : Fragment() {

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
        return inflater.inflate(no.jahnsrud.podium.R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            closeLogin()
        }

        setOnClickListeners()
    }

    fun setOnClickListeners() {
        login_cancelButton.setOnClickListener({
            closeLogin()
        })

        login_loginButton.setOnClickListener({
            login()
        })

        login_registerButton.setOnClickListener({
            openRegister()
        })
    }

    fun login() {
        val email:String = emailField.text.toString()
        val password:String = passwordField.text.toString()

        if (UserHelper().isInputValid (email, password)) {

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context as Activity, OnCompleteListener<AuthResult> { task ->

                    if (task.isSuccessful) {
                        print("Logged in!")
                        closeLogin()
                    } else {
                        print("Logged in!")
                    }

                })

        } else {
            view?.let { Snackbar.make(it, "Something went wrong", Snackbar.LENGTH_SHORT).show() }

        }
    }

    fun openRegister() {

        val fragment: Fragment = RegisterFragment()

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

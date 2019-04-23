package no.jahnsrud.podium.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_register.*

import no.jahnsrud.podium.R

class RegisterFragment : Fragment() {

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

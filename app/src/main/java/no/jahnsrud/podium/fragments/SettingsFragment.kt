package no.jahnsrud.podium.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_settings.*
import no.jahnsrud.podium.activities.LoginActivity
import no.jahnsrud.podium.R
import no.jahnsrud.podium.logic.UserHelper

class SettingsFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        settings_openLoginButton.setOnClickListener({
           openLogin()
        })

        settingsButton1.setOnClickListener({displayNotAvailable()})
        settingsButton2.setOnClickListener({displayNotAvailable()})
        settingsButton3.setOnClickListener({displayNotAvailable()})

        updateStatus()

    }

    fun displayNotAvailable() {
        view?.let { Snackbar.make(it, "Coming soon 🎉", Snackbar.LENGTH_SHORT).show() }

    }

    override fun onResume() {
        super.onResume()

        updateStatus()
    }

    private fun updateStatus() {
        if (UserHelper().isLoggedIn()) {
            settings_openLoginButton.text = "${UserHelper().getLoggedInEmail()}"
        } else {
            settings_openLoginButton.text = "Login to get started"
        }

    }

    private fun openLogin() {

        if (!UserHelper().isLoggedIn()) {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)

        } else {

            // TODO: Improve by asking for user confirmation
            UserHelper().signOut()
            updateStatus()
        }
    }


}

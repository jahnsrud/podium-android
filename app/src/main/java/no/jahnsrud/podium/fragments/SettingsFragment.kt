package no.jahnsrud.podium.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_settings.*
import no.jahnsrud.podium.LoginActivity
import no.jahnsrud.podium.PlaybackActivity
import no.jahnsrud.podium.R

class SettingsFragment : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        loginButton.setOnClickListener({
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        })

    }


}

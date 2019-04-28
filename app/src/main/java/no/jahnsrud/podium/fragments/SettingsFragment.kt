package no.jahnsrud.podium.fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_playback.*
import kotlinx.android.synthetic.main.fragment_settings.*
import no.jahnsrud.podium.AudioPlayer
import no.jahnsrud.podium.activities.LoginActivity
import no.jahnsrud.podium.R
import no.jahnsrud.podium.Settings
import no.jahnsrud.podium.logic.UserHelper

class SettingsFragment : androidx.fragment.app.Fragment(), SeekBar.OnSeekBarChangeListener  {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onStart() {
        super.onStart()

        configureOnClickListeners()
        updateStatus()

    }

    fun configureOnClickListeners() {
        settings_openLoginButton.setOnClickListener({openLogin()})

        settingsButton1.setOnClickListener({displayNotAvailable()})
        settingsButton2.setOnClickListener({displayNotAvailable()})
        settingsButton3.setOnClickListener({displayNotAvailable()})

        // backwardSeekBar.setOnSeekBarChangeListener(this)
        // forwardSeekBar.setOnSeekBarChangeListener(this)

        // backwardSeekBar.setProgress(Settings.getSeekBackward())
        // forwardSeekBar.setProgress(Settings.getSeekForward())
    }

    fun displayNotAvailable() {
        view?.let { Snackbar.make(it, "Coming soon 🎉", Snackbar.LENGTH_SHORT).show() }

    }

    // SeekBar implementation
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        if (seekBar == this.backwardSeekBar) {
            Settings.setSeekBackwardInterval(progress)
        } else if (seekBar == this.forwardSeekBar) {
            Settings.setSeekForwardInterval(progress)

        }


    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

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

package no.jahnsrud.podium

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.view.View
import androidx.transition.Visibility
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.playPauseButton
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.navigation.TabManager


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    /**
     * Much of the Tab Bar logic is inspired by the MultiNavProject by moallemi
     * I adapted much of the logic, but made improvements, too
     * https://github.com/moallemi/MultiNavHost
     */

    private val tabManager: TabManager by lazy { TabManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        tabBar.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            tabManager.currentController = tabManager.libraryTabController
        }

        // TODO: Dynamic
        updatePlaybackBar()

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        tabManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        tabManager.onRestoreInstanceState(savedInstanceState)
    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        tabManager.supportNavigateUpTo(upIntent)
    }

    override fun onBackPressed() {
        tabManager.onBackPressed()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        tabManager.switchTab(menuItem.itemId)
        return true
    }


    fun openPlayback(view: View) {
        val intent = Intent(this@MainActivity, PlaybackActivity::class.java)
        startActivity(intent)

    }

    override fun onResume() {
        super.onResume()

        // TODO: Dynamic
        updatePlaybackBar()
    }

    fun updatePlaybackBar() {
        episodeTextView.text = "Episode"
        podcastTextView.text = "PODCAST"

        if (AudioPlayer.isPlaying) {
            playPauseButton.text = "❙❙"
        } else {
            playPauseButton.text = "▶"
        }
    }

    fun playbackBarInteraction(view: View) {
        Snackbar.make(view, "Coming soon!", Snackbar.LENGTH_LONG).show()
    }


}

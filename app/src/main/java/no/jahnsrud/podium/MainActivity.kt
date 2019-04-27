package no.jahnsrud.podium

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Visibility
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.playPauseButton
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.navigation.TabManager
import android.hardware.SensorManager
import android.widget.Toast
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import androidx.core.content.ContextCompat.getSystemService
import android.hardware.Sensor.TYPE_ACCELEROMETER








class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    /**
     * Much of the Tab Bar logic is inspired by the MultiNavProject by moallemi
     * I adapted much of the logic, but made improvements, too
     * https://github.com/moallemi/MultiNavHost
     */

    private val tabManager: TabManager by lazy { TabManager(this) }

    private var mSensorManager: SensorManager? = null
    private var mSensorListener: ShakeEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)


        tabBar.setOnNavigationItemSelectedListener(this)


        if (savedInstanceState == null) {
            tabManager.currentController = tabManager.libraryTabController
        }

        configureNavController()

        // TODO: Dynamic
        updatePlaybackBar()

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensorListener = ShakeEventListener()

        mSensorListener!!.setOnShakeListener(object : ShakeEventListener.OnShakeListener {

            override fun onShake() {
                Toast.makeText(this@MainActivity, "Shake!", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onPause() {
        mSensorManager?.unregisterListener(mSensorListener)
        super.onPause()
    }

    fun configureNavController() {
        val navController = findNavController(R.id.libraryTab)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


    // TODO: Check why this doesn't work anymore

    /*
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        tabManager.onSaveInstanceState(outState)
    }
    */

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

        mSensorManager?.registerListener(
            mSensorListener,
            mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI
        )
    }

    fun updatePlaybackBar() {

        if (AudioPlayer.currentEpisode != null) {

            playbackBar.visibility = View.VISIBLE
            episodeTextView.text = AudioPlayer.currentEpisode?.title ?: ""

            if ((AudioPlayer.currentPodcast != null) && (AudioPlayer.currentPodcast?.title?.length!! > 0)) {
                podcastTextView.text = AudioPlayer.currentPodcast?.title?.toUpperCase()
            } else {
                podcastTextView.text = "NOW PLAYING"
            }


            if (AudioPlayer.isPlaying) {
                playPauseButton.text = "❙❙"
            } else {
                playPauseButton.text = "▶"
            }

        } else {
            playbackBar.visibility = View.GONE

        }
    }

    fun playbackBarInteraction(view: View) {
        AudioPlayer.playPause()
    }


}

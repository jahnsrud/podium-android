package no.jahnsrud.podium.activities

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.playPauseButton
import no.jahnsrud.podium.navigation.TabManager
import android.hardware.SensorManager
import android.widget.Toast
import android.hardware.Sensor
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.AudioPlayer
import no.jahnsrud.podium.R
import no.jahnsrud.podium.Settings
import no.jahnsrud.podium.database.PodcastViewModel
import no.jahnsrud.podium.logic.ShakeEventListener
import no.jahnsrud.podium.models.Podcast


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

        checkFirstLaunch()

        // configureNavController()

        // TODO: Dynamic
        updatePlaybackBar()

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensorListener = ShakeEventListener()

        mSensorListener!!.setOnShakeListener(object : ShakeEventListener.OnShakeListener {

            override fun onShake() {
                // Toast.makeText(this@MainActivity, "Shake!", Toast.LENGTH_SHORT).show()
            }
        })


    }

    fun checkFirstLaunch() {

        Settings.init(this)

        if (Settings.isFirstLaunch()) {

            println("First Launch: Welcome!")
            println("First Launch: Adding recommended podcasts...")

            prepopulatePodcasts()

            Settings.setFirstLaunchFinished()

            Snackbar.make(libraryTabContainer,
                "Welcome to Podium 🎉 I've added some great podcasts to help you get started",
                Snackbar.LENGTH_LONG)
                .setAction("Got it!", View.OnClickListener() {

                })
                .show()


        } else {
            println("Not first launch. Welcome back!")
        }

    }

    fun prepopulatePodcasts() {
        val p1 = Podcast("RR", "Radioresepsjonen", "https://podkast.nrk.no/program/radioresepsjonen.rss","https://gfx.nrk.no/YUaJcOsN9qEw0OXxXzIIxQxpievY45Eh9bi8iIzYBT8w", "", "NRK")
        val p2 = Podcast("SR", "Serial", "","https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "", "This American Life")
        val p3 = Podcast("Material", "Material", "","https://relayfm.s3.amazonaws.com/uploads/broadcast/image_3x/19/material_artwork.png", "", "Relay FM")
        val p4 = Podcast("MPU", "Mac Power Users", "","https://relayfm.s3.amazonaws.com/uploads/broadcast/image_3x/16/mpu_artwork.png", "", "Relay FM")
        val p5 = Podcast("99Invisible", "99% Invisible", "","https://f.prxu.org/96/images/a52a20dd-7b8e-46be-86a0-dda86b0953fc/99-300.png", "", "Roman Mars")
        val p6 = Podcast("ReplyAll", "ReplyAll", "","https://wi-images.condecdn.net/image/Z4wwonZD3Qg/crop/900/f/reply.png", "", "Gimlet")
        val p7 = Podcast("PlanetMoney", "Planey Money", "","https://media.npr.org/assets/img/2018/08/02/npr_planetmoney_podcasttile_sq-7b7fab0b52fd72826936c3dbe51cff94889797a0-s700-c85.jpg", "", "NPR")

        val model = PodcastViewModel(application)
        model.insert(p1)
        model.insert(p2)
        model.insert(p3)
        model.insert(p4)
        model.insert(p5)
        model.insert(p6)
        model.insert(p7)
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
                playPauseButton.setImageResource(R.drawable.icon_pause_mini)
            } else {
                playPauseButton.setImageResource(R.drawable.icon_play_mini)
            }

        } else {
            playbackBar.visibility = View.GONE

        }
    }

    fun playbackBarInteraction(view: View) {
        AudioPlayer.playPause()
        updatePlaybackBar()
    }


}

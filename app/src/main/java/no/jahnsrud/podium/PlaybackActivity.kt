package no.jahnsrud.podium

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.lang.Exception

class PlaybackActivity : AppCompatActivity() {

    val audioPlayer = AudioPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
    }

    fun playPause(view: View) {
        audioPlayer.playPause()


    }
}

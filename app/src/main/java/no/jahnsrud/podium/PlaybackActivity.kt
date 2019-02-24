package no.jahnsrud.podium

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.lang.Exception

class PlaybackActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)
    }

    fun playPause(view: View) {


        mediaPlayer = MediaPlayer()
        // mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC)

        try {
            mediaPlayer.setDataSource("https://sample-videos.com/audio/mp3/crowd-cheering.mp3")
            mediaPlayer.prepareAsync()

            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
        } catch (e:Exception) {
            println(e)
        }


    }
}

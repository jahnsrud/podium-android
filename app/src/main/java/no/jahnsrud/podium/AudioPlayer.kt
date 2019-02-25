package no.jahnsrud.podium

import android.media.MediaPlayer
import java.lang.Exception

open class AudioPlayer() {

    private lateinit var mediaPlayer: MediaPlayer

    init {
        mediaPlayer = MediaPlayer()
        // mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC)

    }

    fun playPause() {

        try {
            mediaPlayer.setDataSource("https://sample-videos.com/audio/mp3/crowd-cheering.mp3")
            mediaPlayer.prepareAsync()

            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            println(e)
        }
    }

}
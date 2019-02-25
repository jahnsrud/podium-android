package no.jahnsrud.podium

import android.media.MediaPlayer
import java.lang.Exception

open class AudioPlayer() {

    private var mediaPlayer: MediaPlayer

    init {
        mediaPlayer = MediaPlayer()
        // mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC)

    }

    fun playFromUrl(url:String) {
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync()

            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun playPause() {

        if (mediaPlayer.isPlaying) {

            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }

    }

}
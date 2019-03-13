package no.jahnsrud.podium

import android.media.MediaPlayer
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast
import java.lang.Exception

object AudioPlayer {

    private var mediaPlayer: MediaPlayer

    init {
        mediaPlayer = MediaPlayer()
        // mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC)

    }

    fun playFromEpisode(episode: Episode) {

        playFromUrl(episode.streamURL)


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
package no.jahnsrud.podium

import android.media.MediaPlayer
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast
import java.lang.Exception

object AudioPlayer {

    private var mediaPlayer: MediaPlayer
    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null

    init {
        mediaPlayer = MediaPlayer()
        // mediaPlayer.setAudioAttributes(AudioAttributes.CONTENT_TYPE_MUSIC)

    }

    /*
    It should not be needed to supply the podcast too
     */
    fun playFromEpisode(episode: Episode, podcast:Podcast) {

        currentPodcast = podcast
        currentEpisode = episode
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

    fun seekBackward() {

    }

    fun seekForward() {

    }

}
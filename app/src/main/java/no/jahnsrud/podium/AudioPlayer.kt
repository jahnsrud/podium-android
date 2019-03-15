package no.jahnsrud.podium

import android.media.MediaPlayer
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast
import java.lang.Exception

object AudioPlayer : MediaPlayer() {

    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null

    init {

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
            setDataSource(url)
            prepareAsync()

            setOnPreparedListener {
                start()
            }
        } catch (e: Exception) {
            println(e)
        }
    }

    fun playPause() {

        if (isPlaying) {

            pause()
        } else {
            start()
        }

    }

    fun seekBackward() {

    }

    fun seekForward() {

    }


}
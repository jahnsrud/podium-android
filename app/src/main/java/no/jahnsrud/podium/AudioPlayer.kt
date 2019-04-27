package no.jahnsrud.podium

import android.media.MediaPlayer
import no.jahnsrud.podium.models.Episode
import no.jahnsrud.podium.models.Podcast
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

    val SEEK_VALUE = 15000

    fun seekBackward() {
        var pos = this.currentPosition
        pos -= SEEK_VALUE
        this.seekTo(pos)

    }

    fun seekForward() {
        var pos = this.currentPosition
        pos += SEEK_VALUE
        this.seekTo(pos)
    }


}
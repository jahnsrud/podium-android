package no.jahnsrud.podium

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast
import java.lang.Exception

class PlaybackActivity : AppCompatActivity() {

    var currentPodcast:Podcast? = null
    var currentEpisode:Episode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        updatePodcast()
    }

    fun updatePodcast() {

        if (AudioPlayer.currentPodcast != null) {
            currentPodcast = AudioPlayer.currentPodcast
        } else {
            currentPodcast = Podcast("", "", "", "")
        }

        if (AudioPlayer.currentEpisode != null) {
            currentEpisode = AudioPlayer.currentEpisode
        } else {
            currentEpisode = Episode("Radioresepsjonen.test", "Testepisode", "Test", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3")
        }

        titleText.text = currentEpisode?.title
        subtitleText.text = currentPodcast?.title
        Glide.with(this).load(currentPodcast?.coverImageUrl).into(coverImageView)

    }

    fun playPause(view: View) {
        AudioPlayer.playPause()

    }

    fun seekBackward(view: View) {
        AudioPlayer.seekBackward()
    }

    fun seekForward(view: View) {
        AudioPlayer.seekForward()
    }

}

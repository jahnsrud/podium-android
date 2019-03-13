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

    val currentPodcast:Podcast = Podcast("1234", "MockPod™", "", "https://gfx.nrk.no/YUaJcOsN9qEw0OXxXzIIxQxpievY45Eh9bi8iIzYBT8w")
    val currentEpisode:Episode = Episode("Radioresepsjonen.test", "Testepisode", "Test", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        updatePodcast()
    }

    fun updatePodcast() {

        titleText.text = currentEpisode.title
        subtitleText.text = currentPodcast.title
        Glide.with(this).load(currentPodcast.coverImageUrl).into(coverImageView)

    }

    fun playPause(view: View) {
        AudioPlayer.playPause()

    }

    fun seekBackward(view: View) {

    }

    fun seekForward(view: View) {

    }

    fun betaPlayFromSource(view: View) {
        AudioPlayer.playFromUrl(currentEpisode.streamURL)

    }
}

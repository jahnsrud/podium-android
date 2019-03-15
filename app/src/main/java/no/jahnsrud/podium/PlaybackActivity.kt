package no.jahnsrud.podium

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast
import java.lang.Exception

class PlaybackActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        updatePodcast()
        updateProgress()


        seekBar.setOnSeekBarChangeListener(this)

    }

    // SeekBar implementation
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar != null) {
            AudioPlayer.seekTo(seekBar.progress)
        }

        updateProgress()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

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
            finish()
        }

        titleText.text = currentEpisode?.title
        subtitleText.text = currentPodcast?.title
        Glide.with(this).load(currentPodcast?.coverImageUrl).into(coverImageView)

    }

    fun updateProgress() {

        // TODO: format and auto update playback

        totalTimeText.text = "${AudioPlayer.duration/1000}"
        timePlayedText.text = "${AudioPlayer.currentPosition/1000}"


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

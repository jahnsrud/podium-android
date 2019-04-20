package no.jahnsrud.podium

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.bumptech.glide.Glide
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast

class PlaybackFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playback, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updatePodcast()
        updateProgress()

        // TODO:
        // seekBar.setOnSeekBarChangeListener(this)


    }


    // SeekBar implementation
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar != null) {
            AudioPlayer.seekTo(progress * 1000)
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
            // TODO: exit
            // finish()
        }

        // TODO!

        // titleText.text = currentEpisode?.title
        // subtitleText.text = currentPodcast?.title
        // Glide.with(this).load(currentPodcast?.coverImageUrl).into(coverImageView)

    }

    fun updateProgress() {

        // TODO: format and auto update playback

        // totalTimeText.text = "${AudioPlayer.duration/1000}"
        // timePlayedText.text = "${AudioPlayer.currentPosition/1000}"


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

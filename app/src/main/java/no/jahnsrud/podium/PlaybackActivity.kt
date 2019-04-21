package no.jahnsrud.podium

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.models.Episode
import no.jahnsrud.podium.models.Podcast

class PlaybackActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        getSupportActionBar()?.hide();

        updatePodcast()
        updateProgress()

        seekBar.setOnSeekBarChangeListener(this)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

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


        titleText.text = currentEpisode?.title
        subtitleText.text = currentPodcast?.title
        Glide.with(this).load(currentPodcast?.coverImageUrl).into(coverImageView)

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

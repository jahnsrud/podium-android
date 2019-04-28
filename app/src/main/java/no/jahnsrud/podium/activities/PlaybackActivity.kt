package no.jahnsrud.podium.activities

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.SeekBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_playback.*
import no.jahnsrud.podium.AudioPlayer
import no.jahnsrud.podium.R
import no.jahnsrud.podium.models.Episode
import no.jahnsrud.podium.models.Podcast
import android.os.Handler
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class PlaybackActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    var currentPodcast: Podcast? = null
    var currentEpisode: Episode? = null

    private val mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playback)

        getSupportActionBar()?.hide();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        updatePodcast()
        updateProgress()

        seekBar.setOnSeekBarChangeListener(this)

        this.runOnUiThread(object : Runnable {

            override fun run() {
                if (AudioPlayer != null) {
                    updatePodcast()
                }
                if (mHandler != null) {
                    mHandler.postDelayed(this, 1000)
                }
            }
        })

    }

    /*
    public override fun onDestroy() {

        mHandler.removeCallbacks(run)
        super.onDestroy()

    }*/

    // SeekBar implementation
    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        AudioPlayer.seekTo(progress);
        seekBar?.setProgress(progress);

        updateProgress()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    fun updatePodcast() {

        if (AudioPlayer.currentPodcast != null) {
            currentPodcast = AudioPlayer.currentPodcast
        } else {
            currentPodcast = Podcast("", "", "", "", "", "")
        }

        if (AudioPlayer.isPlaying) {
            playPauseButton.setImageResource(R.drawable.icon_pause)
        } else {
            playPauseButton.setImageResource(R.drawable.icon_play)
        }

        if (AudioPlayer.currentEpisode != null) {
            currentEpisode = AudioPlayer.currentEpisode
        } else {
            // this.finish()
        }


        titleText.text = currentEpisode?.title

        if (currentPodcast?.title?.length!! > 0) {
            subtitleText.text = currentPodcast?.title!!.toUpperCase()
        } else {
            subtitleText.text = "NOW PLAYING"
        }

        val total = AudioPlayer.duration
        seekBar.setMax(total)

        Glide.with(this)
            .load(currentPodcast?.coverImageUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .placeholder(R.drawable.placeholder_cover)
            .into(coverImageView)

        updateProgress()

    }

    fun updateProgress() {

        timePlayedText.text = "${formatTime(AudioPlayer.currentPosition)}"
        totalTimeText.text = "${formatTime(AudioPlayer.duration)}"


    }

    // Inspired by this code by Joseph Earl
    // https://stackoverflow.com/questions/5548922/how-do-i-correctly-display-the-position-duration-of-a-mediaplayer

    private fun formatTime(ms: Int): String {
        val buf = StringBuffer()

        val hours = (ms / (1000 * 60 * 60)).toInt()
        val minutes = (ms % (1000 * 60 * 60) / (1000 * 60)).toInt()
        val seconds = (ms % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

        buf
            .append(String.format("%02d", hours))
            .append(":")
            .append(String.format("%02d", minutes))
            .append(":")
            .append(String.format("%02d", seconds))

        return buf.toString()
    }

    fun playPause(view: View) {
        AudioPlayer.playPause()
        playPauseButton.startAnimation(bounceAnimation())
        updatePodcast()

    }

    fun seekBackward(view: View) {
        AudioPlayer.seekBackward()
        seekBackButton.startAnimation(bounceAnimation())
        updateProgress()
    }

    fun seekForward(view: View) {
        AudioPlayer.seekForward()
        seekForwardButton.startAnimation(bounceAnimation())
        updateProgress()

    }

    fun bounceAnimation() : Animation {
        return AnimationUtils.loadAnimation(this, R.anim.bounce_animation) as Animation

    }


}

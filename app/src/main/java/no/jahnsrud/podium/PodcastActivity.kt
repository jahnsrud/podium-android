package no.jahnsrud.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_podcast.*
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast

class PodcastActivity : AppCompatActivity() {

    var podcast:Podcast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast)

        if (this.intent.getSerializableExtra("podcast") != null) {
            podcast = intent.getSerializableExtra("podcast") as? Podcast
        } else {
            val mockPod: Podcast = Podcast("", "", "", "")
            mockPod.title = "MockPod™"
            mockPod.id = "hey01"
            mockPod.coverImageUrl = "https://i.pinimg.com/originals/33/07/37/330737871eb6b5258ff38f4d441bfc1e.png"

            this.podcast = mockPod
        }



        populateData()

    }

    fun populateData() {
        titleTextView.setText(podcast?.title)
        Glide.with(coverImageView).load(podcast?.coverImageUrl).into(coverImageView)


    }

    fun shufflePlay(view: View) {

        val episode = Episode("", "", "", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3")
        AudioPlayer.playFromEpisode(episode, podcast!!)

    }


}

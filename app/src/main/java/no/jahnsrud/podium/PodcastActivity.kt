package no.jahnsrud.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_podcast.*
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast

class PodcastActivity : AppCompatActivity() {

    var podcast:Podcast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_podcast)

        val mockPod: Podcast = Podcast("", "", "", "")
        mockPod.title = "MockPod™"
        mockPod.id = "hey01"

        this.podcast = mockPod

        populateData()

    }

    fun populateData() {
        titleTextView.setText(podcast?.title)
    }

    fun shufflePlay(view: View) {

        val episode = Episode("", "", "", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3")
        AudioPlayer.playFromEpisode(episode)

    }


}

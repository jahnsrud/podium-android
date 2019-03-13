package no.jahnsrud.podium

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import no.jahnsrud.podium.Models.Podcast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openPlaybackActivity(view: View) {
        val intent = Intent(this, PlaybackActivity::class.java)
        startActivity(intent)
    }

    fun openPodcast(view: View) {

        val mockPod:Podcast = Podcast("", "", "", "")

        with(mockPod) {
            title = "MockPod"
            id = "hey02"
        }

        val intent = Intent(this, PodcastActivity::class.java)
        // intent.putExtra("podcast", mockPod)
        startActivity(intent)
    }

    fun testAddPod(view: View) {

        val podcast: Podcast = Podcast("", "", "", "")
        podcast.title = "Radioresepsjonen"
        podcast.id = "id1234"

        val manager:PodcastManager = PodcastManager()
        manager.addPodcast(podcast)



    }


}

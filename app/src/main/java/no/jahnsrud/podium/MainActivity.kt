package no.jahnsrud.podium

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
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

        val mockPod:Podcast = Podcast()
        mockPod.title = "MockPod™"
        mockPod.id = "hey01"

        val intent = Intent(this, PodcastActivity::class.java)
        // intent.putExtra("podcast", mockPod)
        startActivity(intent)
    }

    fun testAddPod(view: View) {

        val podcast: Podcast = Podcast()
        podcast.title = "Tes1t Podcast"
        podcast.id = "test02"

        val manager:PodcastManager = PodcastManager()
        manager.addPodcast(podcast)



    }


}

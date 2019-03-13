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

    fun openSearch(view: View) {

        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)

    }


}

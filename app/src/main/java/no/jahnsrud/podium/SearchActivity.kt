package no.jahnsrud.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.net.URL

class SearchActivity : AppCompatActivity() {

    final val FEATURED_URL:String = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        requestFeaturedPodcasts()

    }

    fun requestFeaturedPodcasts() {

        print("Printing request...")
        val apiResponse = URL(FEATURED_URL).readText()
        print(apiResponse)

        Log.d("Test", apiResponse.toString())

    }


}

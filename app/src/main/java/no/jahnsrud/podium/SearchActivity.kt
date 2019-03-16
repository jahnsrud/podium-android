package no.jahnsrud.podium

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import no.jahnsrud.podium.Models.Podcast
import java.io.StringReader
import java.net.URL
import java.util.concurrent.Executors
import kotlin.reflect.jvm.internal.impl.protobuf.Parser

class SearchActivity : AppCompatActivity() {

    val FEATURED_URL:String = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        requestFeaturedPodcasts()

    }

    fun requestFeaturedPodcasts() {

        val feedParser = FeedParser()
        feedParser.requestFeaturedPodcasts(FEATURED_URL)



    }


}

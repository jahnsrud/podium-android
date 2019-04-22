package no.jahnsrud.podium

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import no.jahnsrud.podium.fragments.podcast
import no.jahnsrud.podium.models.Podcast
import java.io.StringReader
import java.net.URL
import java.util.concurrent.Executors

class FeedParser {

    val FEATURED_URL = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"
    val SEARCH_ROOT_URL = "https://itunes.apple.com/search?entity=podcast&limit=80"

    fun requestFeaturedPodcasts() : ArrayList<Podcast> {


        print("Printing request...")
        var allPodcasts = ArrayList<Podcast>()
        Executors.newSingleThreadExecutor().execute {

            val response = URL(FEATURED_URL).readText()

            val klaxon = Klaxon()
            val json = klaxon.parseJsonObject(StringReader("$response"))

            val feed = json.obj("feed")

            val allFeatured : JsonArray<JsonObject>? = feed?.get("entry") as JsonArray<JsonObject>?

            for((index,obj) in allFeatured?.withIndex()!!) {
                println("${index}: ${obj}")

                val title: JsonObject = obj.get("title") as JsonObject
                val realTitle = title.get("label")

                podcast = Podcast("ID_HERE", realTitle.toString(), "FEED_HERE", "COVER_HERE")

                println("Title: " + realTitle)
                // println("Title: " + realTitle)

                println(podcast.toString())

                allPodcasts.add(podcast!!)


            }

        }

        return allPodcasts
    }

    fun searchPodcastDirectory(search: String) {

        if (validateSearchTerm(search)) {

            val searchTerm = "&term=${search}"
            val requestUrl = SEARCH_ROOT_URL + searchTerm

        } else {
            print("Invalid search term")
        }


    }

    fun validateSearchTerm(search: String) : Boolean {

        if (search.length > 0) {
            return true;
        }
        return false;

    }
}
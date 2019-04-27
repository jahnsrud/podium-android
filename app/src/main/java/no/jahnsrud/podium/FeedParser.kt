package no.jahnsrud.podium

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import no.jahnsrud.podium.models.Podcast
import java.io.StringReader
import java.net.URL
import java.util.concurrent.Executors

class FeedParser {

    val FEATURED_URL = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"
    val SEARCH_ROOT_URL = "https://itunes.apple.com/search?entity=podcast&limit=80"


    fun requestFeaturedPodcasts(callBack: (ArrayList<Podcast>) -> Unit)  {

        print("Printing request...")
        var allPodcasts = ArrayList<Podcast>()

        Executors.newSingleThreadExecutor().execute {

            val response = URL(FEATURED_URL).readText()

            val klaxon = Klaxon()
            val json = klaxon.parseJsonObject(StringReader("$response"))

            val feed = json.obj("feed")

            val allFeatured : JsonArray<JsonObject>? = feed?.get("entry") as JsonArray<JsonObject>?

            for((index,jsonObject) in allFeatured?.withIndex()!!) {
                println("${index}: ${jsonObject}")

                // ID: Kommer her!
                val ids:JsonObject = jsonObject.get("id") as JsonObject
                val trackId:String = "0" // ids.get("id") as String


                // Podcast Title
                val title: JsonObject = jsonObject.get("title") as JsonObject
                val realTitle = title.get("label")

                println("Title: " + realTitle)

                // Image

                val itemImages = jsonObject.get("image")
                // val image = itemImages.get(2) as String

                // URL
                // Ikke mulig å hente her...


                // Description
                val description = jsonObject.get("summary").toString()
                print(description)

                val podcast = Podcast(trackId, realTitle.toString(), "NOT_ADDED", "IMAGE", description)

                println(podcast.toString())

                allPodcasts.add(podcast!!)


            }

            callBack(allPodcasts)

        }

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
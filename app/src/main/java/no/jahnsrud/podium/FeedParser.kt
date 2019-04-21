package no.jahnsrud.podium

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import java.io.StringReader
import java.net.URL
import java.util.concurrent.Executors

class FeedParser {

    val FEATURED_URL:String = "https://itunes.apple.com/no/rss/toppodcasts/limit=50/explicit=true/json"
    val SEARCH_ROOT_URL:String = "https://itunes.apple.com/search?entity=podcast&limit=80"

    fun requestFeaturedPodcasts() {

        print("Printing request...")
        Executors.newSingleThreadExecutor().execute {

            val response = URL(FEATURED_URL).readText()

            val klaxon = Klaxon()
            val json = klaxon.parseJsonObject(StringReader("$response"))

            val feed = json.obj("feed")

            val allFeatures : JsonArray<JsonObject>? = feed?.get("entry") as JsonArray<JsonObject>?

            for((index,obj) in allFeatures?.withIndex()!!) {
                // println("Loop Iteration $index on each object")
                println(obj)

                val title: JsonObject = obj.get("title") as JsonObject
                val realTitle = title.get("label")

                println(realTitle)

                // println(obj.get("artist"))

                // val yourObj = Klaxon().parseFromJsonObject<Haltestelle>(obj)
            }
        }
    }

    fun searchPodcastDirectory(search: String) {
        val searchTerm = "&term=${search}"
        val requestUrl = SEARCH_ROOT_URL + searchTerm

    }

}
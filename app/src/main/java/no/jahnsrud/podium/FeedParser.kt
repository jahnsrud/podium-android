package no.jahnsrud.podium

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import java.io.StringReader
import java.net.URL
import java.util.concurrent.Executors

class FeedParser {

    fun requestFeaturedPodcasts(url: String) {

        print("Printing request...")
        Executors.newSingleThreadExecutor().execute({

            val response = URL(url).readText()

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
        })
    }

}
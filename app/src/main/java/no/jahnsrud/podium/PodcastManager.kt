package no.jahnsrud.podium

import android.util.Log
import io.realm.Realm
import io.realm.kotlin.createObject
import no.jahnsrud.podium.Models.Podcast
import java.io.IOException

open class PodcastManager() {

    fun addPodcast(podcast:Podcast) {

        print(podcast.title)

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()

        try {
            realm.createObject(podcast::class.java, podcast.id)
        } catch (e:IOException) {
            realm.cancelTransaction()
            Log.e("Something went wrong", "Cancelling transaction...")
        }

        realm.close()

        betaPrintAllPodcasts()

    }

    fun betaPrintAllPodcasts() {

        val realm = Realm.getDefaultInstance()

        val podcast = realm.where(Podcast::class.java).findFirst()
        if (podcast != null) {
            println("Fant en i databasen: ${podcast.title} & ID: ${podcast.id}")
        }

    }

}
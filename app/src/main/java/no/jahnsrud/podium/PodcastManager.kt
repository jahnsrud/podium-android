package no.jahnsrud.podium

import android.util.Log
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import no.jahnsrud.podium.Models.Podcast
import java.io.IOException

open class PodcastManager() {

    fun addPodcast(podcast:Podcast) {

        print(podcast.title)

        val realm = Realm.getDefaultInstance()

        try {
            val podcast = realm.createObject<Podcast>(podcast.id)
        } catch (e:IOException) {
            Log.e("Something went wrong", "Cancelling transaction...")
        } finally {
        }


        betaPrintAllPodcasts()

    }

    fun getAllPodcasts() : RealmResults<Podcast> {
        val realm = Realm.getDefaultInstance()
        val podcasts = realm.where<Podcast>().findAll()

        return podcasts

    }

    fun betaPrintAllPodcasts() {

        println("🚂 All aboard the print train")

       print(getAllPodcasts().forEach {
            println(it.id)
       })

    }

}
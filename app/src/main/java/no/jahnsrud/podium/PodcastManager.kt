package no.jahnsrud.podium

import android.util.Log
import no.jahnsrud.podium.Models.Podcast
import java.io.IOException

open class PodcastManager() {

    fun addPodcast(podcast:Podcast) {

        print(podcast.title)

        // TODO: FIX


    }

    // TODO: FIX
    /*
    fun getAllPodcasts() : RealmResults<Podcast> {
        val realm: Realm = Realm.getDefaultInstance()
        val podcasts: RealmResults<Podcast> = realm.where<Podcast>().findAll()

        return podcasts

    }

    fun betaPrintAllPodcasts() {

        println("🚂 All aboard the print train")
        println("Number of Podcasts: ${getAllPodcasts().size}")

       print(getAllPodcasts().forEach {
            println(it.id)
       })

    }
    */

}
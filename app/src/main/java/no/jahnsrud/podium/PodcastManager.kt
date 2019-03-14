package no.jahnsrud.podium

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import no.jahnsrud.podium.Database.PodcastViewModel
import no.jahnsrud.podium.Models.Podcast
import kotlin.coroutines.CoroutineContext

open class PodcastManager() {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    fun addPodcast(podcast:Podcast) {

        val model = PodcastViewModel(application = Application())
        model.insert(podcast)


    }

}
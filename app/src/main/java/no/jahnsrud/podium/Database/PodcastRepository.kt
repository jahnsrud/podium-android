package no.jahnsrud.podium.Database

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import no.jahnsrud.podium.Models.Podcast

class PodcastRepository(private val podcastDao: PodcastDao) {

    val allWords: LiveData<List<Podcast>> = podcastDao.getAllPodcasts()

    @WorkerThread
    suspend fun insert(podcast: Podcast) {
        podcastDao.insert(podcast)
    }


}
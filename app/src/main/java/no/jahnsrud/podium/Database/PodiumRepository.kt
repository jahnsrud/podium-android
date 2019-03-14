package no.jahnsrud.podium.Database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.support.annotation.WorkerThread
import no.jahnsrud.podium.Models.Episode
import no.jahnsrud.podium.Models.Podcast

class PodiumRepository {

    lateinit var podcastDao: PodcastDao
    lateinit var episodeDao: EpisodeDao
    lateinit var allPodcasts: LiveData<List<Podcast>>
    lateinit var allEpisodes: LiveData<List<Episode>>

    constructor(podcastDao: PodcastDao) {
        this.podcastDao = podcastDao
        allPodcasts = podcastDao.getAllPodcasts()
    }

    constructor(episodeDao: EpisodeDao){
        this.episodeDao = episodeDao
        allEpisodes = episodeDao.getAllEpisodes()
    }


    @WorkerThread
    suspend fun insert(podcast: Podcast) {
        podcastDao.insert(podcast)
    }

    @WorkerThread
    suspend fun insert(episode: Episode) {
        // episodeDao
    }


}
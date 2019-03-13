package no.jahnsrud.podium.Database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jahnsrud.podium.Models.Podcast
import kotlin.coroutines.CoroutineContext

class PodcastViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PodcastRepository
    val allPodcasts: LiveData<List<Podcast>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val podcastDao = PodcastRoomDatabase.getDatabase(application, scope).podcastDao()
        repository = PodcastRepository(podcastDao)
        allPodcasts = repository.allPodcasts

    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(podcast: Podcast) = scope.launch(Dispatchers.IO) {
        repository.insert(podcast)
    }




}
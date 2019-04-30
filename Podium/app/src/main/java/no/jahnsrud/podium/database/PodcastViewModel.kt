package no.jahnsrud.podium.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jahnsrud.podium.models.Podcast
import kotlin.coroutines.CoroutineContext

class PodcastViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PodiumRepository
    val allPodcasts: LiveData<List<Podcast>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val podcastDao = PodcastRoomDatabase.getDatabase(application, scope).podcastDao()
        repository = PodiumRepository(podcastDao)
        allPodcasts = repository.allPodcasts

    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(podcast: Podcast) = scope.launch(Dispatchers.IO) {
        repository.insert(podcast)
    }

    fun delete(podcast: Podcast) = scope.launch(Dispatchers.IO) {
        repository.delete(podcast)
    }



}
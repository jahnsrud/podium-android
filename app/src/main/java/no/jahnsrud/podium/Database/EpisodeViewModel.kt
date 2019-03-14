package no.jahnsrud.podium.Database

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jahnsrud.podium.Models.Episode
import kotlin.coroutines.CoroutineContext

class EpisodeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PodiumRepository
    val allEpisodes: LiveData<List<Episode>>

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val episodeDao = EpisodeRoomDatabase.getDatabase(application, scope).episodeDao()
        repository = PodiumRepository(episodeDao)
        allEpisodes = repository.allEpisodes

    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(episode: Episode) = scope.launch(Dispatchers.IO) {
        repository.insert(episode)
    }


}
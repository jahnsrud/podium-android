package no.jahnsrud.podium.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import no.jahnsrud.podium.models.Episode
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
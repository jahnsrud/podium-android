package no.jahnsrud.podium.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.jahnsrud.podium.models.Episode

@Dao

interface EpisodeDao {

    // TODO: Riktig Query
    @Query("SELECT * FROM episodes ORDER BY title ASC")
    fun getAllEpisodes(): LiveData<List<Episode>>

    @Insert
    fun insert(episode: Episode)

    @Query("DELETE FROM episodes")
    fun deleteAll()


}
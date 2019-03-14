package no.jahnsrud.podium.Database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import no.jahnsrud.podium.Models.Episode

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
package no.jahnsrud.podium.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.jahnsrud.podium.models.Podcast

@Dao

interface PodcastDao {

    @Query("SELECT * FROM podcasts ORDER BY title ASC")
    fun getAllPodcasts(): LiveData<List<Podcast>>

    @Insert
    fun insert(podcast: Podcast)

    @Query("DELETE FROM podcasts")
    fun deleteAll()

}
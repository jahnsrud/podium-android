package no.jahnsrud.podium.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import no.jahnsrud.podium.Models.Podcast

@Dao

interface PodcastDao {

    @Query("SELECT * FROM podcasts ORDER BY title ASC")
    fun getAllPodcasts(): LiveData<List<Podcast>>

    @Insert
    fun insert(podcast: Podcast)

    @Query("DELETE FROM podcasts")
    fun deleteAll()

}
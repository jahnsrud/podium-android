package no.jahnsrud.podium.Database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
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
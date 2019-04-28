package no.jahnsrud.podium.database

import androidx.lifecycle.LiveData
import androidx.room.*
import no.jahnsrud.podium.models.Podcast

@Dao

interface PodcastDao {

    @Query("SELECT * FROM podcasts ORDER BY title ASC")
    fun getAllPodcasts(): LiveData<List<Podcast>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(podcast: Podcast)

    @Delete
    fun delete(podcast: Podcast)

    @Query("SELECT COUNT(*) from podcasts")
    fun usersCount() : List<Int>

    @Query("DELETE FROM podcasts")
    fun deleteAll()

}
package no.jahnsrud.podium.database

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.jahnsrud.podium.models.Episode
import no.jahnsrud.podium.models.Podcast

@Database(entities = [Episode::class], version = 1, exportSchema = false)
abstract class EpisodeRoomDatabase : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao

    companion object {
        @Volatile
        private var INSTANCE: EpisodeRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EpisodeRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,

                    EpisodeRoomDatabase::class.java,
                    "episodes"
                )
                    .addCallback(EpisodeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                return instance
            }
        }


        private class EpisodeDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.episodeDao())
                    }
                }
            }

            fun populateDatabase(episodeDao: EpisodeDao) {
                episodeDao.deleteAll()

                val p1 = Podcast("RR", "Radioresepsjonen", "","https://gfx.nrk.no/YUaJcOsN9qEw0OXxXzIIxQxpievY45Eh9bi8iIzYBT8w", "")


                episodeDao.insert(Episode("ep1", "23. april-episoden", "Fun, fun", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", p1))
                episodeDao.insert(Episode("ep2", "Den med placeholder", "Fun 2", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", p1))
                episodeDao.insert(Episode("ep3", "Den med placeholder", "Fun 3", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", p1))
                episodeDao.insert(Episode("ep4", "Den med placeholder", "Fun 4", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", p1))
            }
        }

    }

}

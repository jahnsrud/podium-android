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

                episodeDao.insert(Episode("ep1", "The Exciting Episode", "This episode is all about something something", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep2", "The Placeholder", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep3", "The Follow-up", "Magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep4", "The Good and The Bad", "Nisli ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep5", "The Placeholder", "Nisli ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep6", "The Race", "Nisli ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep7", "The Actually Fun One", "Nisli ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
                episodeDao.insert(Episode("ep8", "The Somewhat Good Episode", "Nisli ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatu", "https://nl.nrk.no/podkast/aps/10908/radioresepsjonen_2018-12-17_1255_3633.MP3", "01/01/2019"))
            }
        }

    }

}

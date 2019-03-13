package no.jahnsrud.podium.Database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.jahnsrud.podium.Models.Podcast

@Database(entities = [Podcast::class], version = 1, exportSchema = false)
abstract class PodcastRoomDatabase : RoomDatabase() {
    abstract fun podcastDao(): PodcastDao

    companion object {
        @Volatile
        private var INSTANCE: PodcastRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PodcastRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,

                    PodcastRoomDatabase::class.java,
                    "podcasts"
                )
                    .addCallback(PodcastDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                return instance
            }
        }


        private class PodcastDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.podcastDao())
                    }
                }
            }

            fun populateDatabase(podcastDao: PodcastDao) {
                podcastDao.deleteAll()

                val p1 = Podcast("RR", "RR.test", "","https://gfx.nrk.no/YUaJcOsN9qEw0OXxXzIIxQxpievY45Eh9bi8iIzYBT8w")
                val p2 = Podcast("SR", "Serial.test", "","https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png")

                podcastDao.insert(p1)
                podcastDao.insert(p2)

            }
        }

    }

}
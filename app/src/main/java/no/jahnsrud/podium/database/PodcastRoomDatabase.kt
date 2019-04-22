package no.jahnsrud.podium.database

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.jahnsrud.podium.models.Podcast

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

                val p1 = Podcast("RR", "Radioresepsjonen", "","https://gfx.nrk.no/YUaJcOsN9qEw0OXxXzIIxQxpievY45Eh9bi8iIzYBT8w", "")
                val p2 = Podcast("SR", "Serial", "","https://www.creativelive.com/blog/wp-content/uploads/2014/12/seriallogo.png", "")
                val p3 = Podcast("Material", "Material", "","https://relayfm.s3.amazonaws.com/uploads/broadcast/image_3x/19/material_artwork.png", "")
                val p4 = Podcast("MPU", "Mac Power Users", "","https://relayfm.s3.amazonaws.com/uploads/broadcast/image_3x/16/mpu_artwork.png", "")
                val p5 = Podcast("99Invisible", "99% Invisible", "","https://f.prxu.org/96/images/a52a20dd-7b8e-46be-86a0-dda86b0953fc/99-300.png", "")

                podcastDao.insert(p1)
                podcastDao.insert(p2)
                podcastDao.insert(p3)
                podcastDao.insert(p4)
                podcastDao.insert(p5)



            }
        }

    }

}
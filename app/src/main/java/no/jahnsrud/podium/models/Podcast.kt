package no.jahnsrud.podium.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "podcasts")
open class Podcast (
    @ColumnInfo(name = "id") var id:String,
    @PrimaryKey var title:String,
    @ColumnInfo(name = "feedUrl") var feedUrl:String,
    @ColumnInfo(name = "coverImageUrl") var coverImageUrl:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "publisher") var publisher:String


) : Serializable {
    override fun toString(): String {
        return "Podcast(id='$id', " +
                "title='$title', " +
                "feedUrl='$feedUrl', " +
                "coverImageUrl='$coverImageUrl', " +
                "publisher='$publisher', " +
                "description='$description')"
    }
}


package no.jahnsrud.podium.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "podcasts")
open class Podcast (
    @PrimaryKey var id:String,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "feedUrl") var feedUrl:String,
    @ColumnInfo(name = "coverImageUrl") var coverImageUrl:String,
    @ColumnInfo(name = "description") var description:String


) : Serializable {
    override fun toString(): String {
        return "Podcast(id='$id', title='$title', feedUrl='$feedUrl', coverImageUrl='$coverImageUrl', description='$description')"
    }
}


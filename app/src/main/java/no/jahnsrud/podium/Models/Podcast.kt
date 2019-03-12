package no.jahnsrud.podium.Models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "podcasts")
open class Podcast(
    @PrimaryKey var id:String,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "feedUrl") var feedUrl:String,
    @ColumnInfo(name = "coverImageUrl") var coverImageUrl:String


)
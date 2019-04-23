package no.jahnsrud.podium.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
open class Episode (
    @PrimaryKey var id:String,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "streamURL") var streamURL:String,
    @ColumnInfo(name = "podcast") var podcast:Podcast


/*
Length in seconds
Playback Position
finishedListening
isAvailableOffline
Podcast
GUID
publishDate
lastPlayedDate
lastUpdated
  season, episodenumber, episodetype

 */

    )
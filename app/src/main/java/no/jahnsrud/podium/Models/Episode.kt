package no.jahnsrud.podium.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episodes")
open class Episode (
    @PrimaryKey var id:String,
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "description") var description:String,
    @ColumnInfo(name = "streamURL") var streamURL:String

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
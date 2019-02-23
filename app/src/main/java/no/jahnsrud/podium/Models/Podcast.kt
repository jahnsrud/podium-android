package no.jahnsrud.podium.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Podcast : RealmObject() {

    @PrimaryKey
    var id:String? = null
    var title:String? = null
    var feedUrl:String? = null
    var coverImageUrl:String? = null


}
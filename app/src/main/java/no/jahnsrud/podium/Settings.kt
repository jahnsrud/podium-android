package no.jahnsrud.podium

import android.content.Context
import android.content.SharedPreferences

object Settings {
    val PREFS_FILENAME = "no.jahnsrud.podium.prefs"
    var prefs: SharedPreferences? = null

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, 0)

    }

    fun setSeekForwardInterval(interval: Int) {
        val editor = prefs!!.edit()
        editor.putInt("seekForward", interval)
        editor.apply()
    }

    fun setSeekBackwardInterval(interval: Int) {
        val editor = prefs!!.edit()
        editor.putInt("seekBackward", interval)
        editor.apply()
    }

}
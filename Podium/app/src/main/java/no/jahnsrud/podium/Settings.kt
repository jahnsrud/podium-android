package no.jahnsrud.podium

import android.content.Context
import android.content.SharedPreferences

object Settings {
    val PREFS_FILENAME = "no.jahnsrud.podium.prefs"
    var prefs: SharedPreferences? = null
    val DEFAULT_SEEK_VALUE = 15

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

    fun getSeekBackward() : Int {
        return prefs!!.getInt("seekBackward", DEFAULT_SEEK_VALUE)

    }

    fun getSeekForward() : Int {
        return prefs!!.getInt("seekForward", DEFAULT_SEEK_VALUE)
    }

    fun setFirstLaunchFinished() {
        val editor = prefs!!.edit()
        editor.putBoolean("firstLaunch", false)
        editor.apply()
    }

    fun isFirstLaunch() : Boolean {
        return prefs!!.getBoolean("firstLaunch", true)
    }

}
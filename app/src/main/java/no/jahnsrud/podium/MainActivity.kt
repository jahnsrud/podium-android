package no.jahnsrud.podium

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View


class MainActivity : AppCompatActivity() {

    val fragment1: Fragment = LibraryFragment()
    val fragment2: Fragment = SearchFragment()
    val fragment3: Fragment = SettingsFragment()
    val fm = supportFragmentManager
    var active = fragment1


    @SuppressLint("ResourceType")
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.libraryFragment-> {

                loadFragment(LibraryFragment())

                return@OnNavigationItemSelectedListener true
            }
            R.id.searchFragment -> {

                loadFragment(SearchFragment())


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                loadFragment(SettingsFragment())

                return@OnNavigationItemSelectedListener true
            }
        }


        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)



    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment 
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment!!)
                .commit()
            return true
        }
        return false
    }

    fun openPlayback(view: View) {
        val intent = Intent(this@MainActivity, PlaybackActivity::class.java)
        startActivity(intent)
    }
}

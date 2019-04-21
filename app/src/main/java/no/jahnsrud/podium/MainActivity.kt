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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_library.*


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

                runOnUiThread {
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    // your code to update the UI thread here
                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.searchFragment -> {

                runOnUiThread {
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;

                }

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {

                runOnUiThread {
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;

                }

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

        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "1").commit();



        // navView.setupWithNavController(Navigation.findNavController(this, R.id.main_container))



    }


    fun openPlayback(view: View) {
        val intent = Intent(this@MainActivity, PlaybackActivity::class.java)
        startActivity(intent)
    }
}

package no.jahnsrud.podium

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.libraryFragment-> {
                textMessage.setText(R.string.title_home)



                return@OnNavigationItemSelectedListener true
            }
            R.id.searchFragment -> {
                textMessage.setText(R.string.title_search)


                val manager = supportFragmentManager
                val transaction = manager.beginTransaction()
                transaction.add(R.layout.fragment_search, this.fragment)
                transaction.commit()


                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                textMessage.setText(R.string.title_settings)
                return@OnNavigationItemSelectedListener true
            }
        }


        false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}

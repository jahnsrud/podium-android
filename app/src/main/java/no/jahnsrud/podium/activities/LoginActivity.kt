package no.jahnsrud.podium.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import no.jahnsrud.podium.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        getSupportActionBar()?.hide();

    }
}

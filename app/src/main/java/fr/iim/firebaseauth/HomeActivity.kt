package fr.iim.firebaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.iim.firebaseauth.ui.search.SearchFragment
import fr.iim.firebaseauth.ui.welcome.WelcomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if (savedInstanceState == null) {
            val intentValue = intent.getStringExtra(EXTRA_NAME)
            Log.d("HomeData1", intentValue.toString())
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WelcomeFragment.newInstance(intentValue.toString()))
                .commitNow()
            supportFragmentManager.beginTransaction()
                .replace(R.id.layout_city, SearchFragment.newInstance())
                .commitNow()
        }
    }


    companion object {
        const val EXTRA_NAME = "HELLO_NAME"
    }
}
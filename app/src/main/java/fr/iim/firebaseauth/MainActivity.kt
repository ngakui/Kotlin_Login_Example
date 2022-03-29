package fr.iim.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.iim.firebaseauth.ui.form.FormFragment

class MainActivity : AppCompatActivity(), FormFragment.FormFragmentInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, FormFragment.newInstance())
            .commit()
    }

    override fun OnHomeClickListener(firstName: String, password: String) {
        Log.d(LOG_TAB, "click event sent")

        val intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
            Log.d("HomeClick", firstName)
            putExtra(HomeActivity.EXTRA_NAME,firstName)
        }
        startActivity(intent)
    }

    companion object {
        private val LOG_TAB = "MainActivity"
    }
}
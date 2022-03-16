package com.example.beosztasapp.activities

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beosztasapp.R
import com.example.beosztasapp.activites.RegisterActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home_alk.*
import kotlinx.android.synthetic.main.activity_home_alk.toolbar


class HomeAlkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_alk)
        setSupportActionBar(toolbar)
        val nev:String = intent.getStringExtra("nev").toString()
        Toast.makeText(this, "you are " + nev, Toast.LENGTH_LONG).show()
        val res: Resources = resources
        val text: String = res.getString(com.example.beosztasapp.R.string.udvozlet,nev )
        home_alk_title.setText(text)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.alk_beoszt -> {
                Toast.makeText(applicationContext, "Beosztásom clicked", Toast.LENGTH_LONG).show()
                this.startActivity(Intent(this,CalendarActivity::class.java))
                true
            }
            R.id.alk_ertesit ->{
                Toast.makeText(applicationContext, "Értesítéseim clicked", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.alk_szab ->{
                Toast.makeText(applicationContext, "Korábbi szabik clicked", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.alk_szem ->{
                Toast.makeText(applicationContext, "Személyes adatok clicked", Toast.LENGTH_LONG).show()
                this.startActivity(Intent(this,RegisterActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
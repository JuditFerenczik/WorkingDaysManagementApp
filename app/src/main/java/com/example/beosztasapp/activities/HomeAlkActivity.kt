package com.example.beosztasapp.activities

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.beosztasapp.R
import com.example.beosztasapp.activites.LoginActivity
import kotlinx.android.synthetic.main.activity_home_alk.*
import kotlinx.android.synthetic.main.activity_home_alk.toolbar


class HomeAlkActivity : AppCompatActivity() {
    var nev: String? = null
    var ered: Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_alk)
        setSupportActionBar(toolbar)
        sqliteHelper = SqlHelper(this)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)
       // Toast.makeText(this, "you are " + nev + " - " + ered, Toast.LENGTH_LONG).show()
        val res: Resources = resources
        val text: String = res.getString(com.example.beosztasapp.R.string.udvozlet, firstName)
        home_alk_title.setText(text)
        if (sqliteHelper.getSzemely(ered).jelszo == "Tesztjelszo") {
            Toast.makeText(
                this,
                "Alapértelmezett jelszót használ, változtassa meg a személyes adatok menüpontban!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.alk_beoszt -> {
                // Toast.makeText(applicationContext, "Beosztásom clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CalendarActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                true
            }
            R.id.alk_ertesit -> {
                //  Toast.makeText(applicationContext, "Értesítéseim clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ErtesitesActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                true
            }
            R.id.alk_szab -> {
                //  Toast.makeText(applicationContext, "Szabadságok clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, SzabiActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                return true
            }
            R.id.alk_szem -> {
                // Toast.makeText(applicationContext, "Személyes adatok clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                return true
            }
            R.id.alk_kij -> {
                //   Toast.makeText(applicationContext, "Kijelentkezés clicked", Toast.LENGTH_LONG).show()
                this.startActivity(Intent(this, LoginActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
package com.example.beosztasapp.activities

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.beosztasapp.R
import com.example.beosztasapp.activites.LoginActivity
import com.example.beosztasapp.activites.RegisterActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.toolbar


class HomeActivity : AppCompatActivity() {

    var nev: String? = null
    var ered: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)

        // Toast.makeText(this, "you are " + nev + " - " + ered, Toast.LENGTH_LONG).show()
        val res: Resources = resources
        val text: String = res.getString(com.example.beosztasapp.R.string.udvozlet, firstName)

        home_fon_title.setText(text)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_fon, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fon_beoszt -> {
                // Toast.makeText(applicationContext, "Beosztásom clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, CalendarActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                true
            }
            R.id.fon_ertesit -> {
                //  Toast.makeText(applicationContext, "Beosztásom clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, ErtesitesFActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                true
            }
            R.id.fon_szab -> {
                // Toast.makeText(applicationContext, "Szabadságok clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, SzabiActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                return true
            }
            R.id.fon_szem -> {
                // Toast.makeText(applicationContext, "Személyes adatok clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                return true
            }
            R.id.fon_reg -> {
                //  Toast.makeText(applicationContext, "Regisztráció clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, RegisterActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                return true
            }
            R.id.fon_beosztott -> {
                //   Toast.makeText(applicationContext, "Beosztottaim clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UsersListActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                startActivity(intent)
                true
            }
            R.id.fon_kij -> {
                //  Toast.makeText(applicationContext, "Sikeres kijelentkezés!", Toast.LENGTH_LONG).show()

                this.startActivity(Intent(this, LoginActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
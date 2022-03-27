package com.example.beosztasapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

class ErtesitesFActivity : AppCompatActivity() {

    lateinit var ertesitesekF: ArrayList<ErtesitesF>
    private lateinit var recyclerView: RecyclerView
    private var adapter: ErtesitesFRecycleAdapter? = null
    private var ertesitesF: ErtesitesF? = null
    var nev: String? = null
    var ered: Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ertesites_factivity)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)
        sqliteHelper = SqlHelper(this)


        recyclerView = findViewById<View>(R.id.recyclerViewErtesitesF) as RecyclerView

        initRecycler()
        getErtesitesek()
        adapter?.setOnClickElfogadItem {
            elfogadErtesites(it.keresek_id)
            getErtesitesek()

        }
        adapter?.setOnClickElutasitItem {
            elutasitErtesites(it.keresek_id)
            getErtesitesek()

        }


    }

    override fun onBackPressed() {
        // Toast.makeText(this, szemely_id.toString() + nev+isFonok.toString(), Toast.LENGTH_LONG).show()
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("szemelyid", ered)
        intent.putExtra("nev", nev)
        startActivity(intent)

    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ErtesitesFRecycleAdapter()
        recyclerView.adapter = adapter

    }

    private fun getErtesitesek() {
        val ertesitesekF = sqliteHelper.ertesitesekF(ered)
        if (ertesitesekF.size == 0) {
            Toast.makeText(this, "Nincs megjelenítendő értesítés!", Toast.LENGTH_LONG).show()
        }
        adapter?.addItems(ertesitesekF)
    }

    private fun elfogadErtesites(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztos elfogadja a szabadságot?")
        builder.setCancelable(true)
        builder.setPositiveButton("Igen") { dialog, _ ->
            sqliteHelper.elfogadSzabi(id)
            Toast.makeText(this, "Kérelem elfogadva!", Toast.LENGTH_LONG).show()
            dialog.dismiss()
            val intent = Intent(this, ErtesitesFActivity::class.java)
            intent.putExtra("szemelyid", ered)
            intent.putExtra("nev", nev)
            startActivity(intent)
        }
        builder.setNegativeButton("Nem") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun elutasitErtesites(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztos eltörli a szabadságot?")
        builder.setCancelable(true)
        builder.setPositiveButton("Igen") { dialog, _ ->
            sqliteHelper.elutasitSzabi(id)
            Toast.makeText(this, "Kérelem elutasítva!", Toast.LENGTH_LONG).show()
            dialog.dismiss()
            val intent = Intent(this, ErtesitesFActivity::class.java)
            intent.putExtra("szemelyid", ered)
            intent.putExtra("nev", nev)
            startActivity(intent)
        }
        builder.setNegativeButton("Nem") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }
}

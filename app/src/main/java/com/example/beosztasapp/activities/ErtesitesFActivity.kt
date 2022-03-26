package com.example.beosztasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

class ErtesitesFActivity : AppCompatActivity() {
    //lateinit var contacts2: ArrayList<szemelyek>
    lateinit var ertesitesekF: ArrayList<ErtesitesF>
    var nev:String? = null
    var ered:Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ertesites_factivity)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid",-1)
        sqliteHelper = SqlHelper(this)


        val container = findViewById<View>(R.id.recyclerViewErtesitesF) as RecyclerView

       val ertesitesekF = sqliteHelper.ertesitesekF(ered)


        val adapter = ErtesitesFRecycleAdapter(ertesitesekF)

        container.adapter = adapter
        adapter?.setOnClickElfogadItem {
            elfogadErtesites(it.keresek_id)
        }
        adapter?.setOnClickElutasitItem {
            elutasitErtesites(it.keresek_id)
        }
        container.layoutManager = LinearLayoutManager(this)
        // That's all!


    }
    private fun elfogadErtesites(id:Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztos elfogadja a szabadságot?")
        builder.setCancelable(true)
        builder.setPositiveButton("Igen"){dialog, _->
            sqliteHelper.elfogadSzabi(id)

            dialog.dismiss()
        }
        builder.setNegativeButton("Nem"){dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }
    private fun elutasitErtesites(id:Int){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztos eltörli a szabadságot?")
        builder.setCancelable(true)
        builder.setPositiveButton("Igen"){dialog, _->
            sqliteHelper.elutasitSzabi(id)

            dialog.dismiss()
        }
        builder.setNegativeButton("Nem"){dialog, _->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }
    }

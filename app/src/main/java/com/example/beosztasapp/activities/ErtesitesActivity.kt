package com.example.beosztasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

class ErtesitesActivity : AppCompatActivity() {
    lateinit var keres: ArrayList<Keresek>
    var nev:String? = null
    var ered:Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ertesites)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid",-1)
        sqliteHelper = SqlHelper(this)

        val container = findViewById<View>(R.id.recyclerViewErtesites) as RecyclerView
        val keres = sqliteHelper.ertesitesek(ered)

        val adapter = ErtesitesRecyclerActivity.ErtesitesRecycleAdapter(keres)

        container.adapter = adapter

        container.layoutManager = LinearLayoutManager(this)



    }
}
package com.example.beosztasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beosztasapp.R

class SzabiActivity : AppCompatActivity() {
    lateinit var keres: ArrayList<Keresek>
    var nev:String? = null
    var ered:Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_szabi)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid",-1)
        sqliteHelper = SqlHelper(this)

        // ...
        // Lookup the recyclerview in activity layout
        val container = findViewById<View>(R.id.recyclerViewSzabi) as RecyclerView
        // Initialize contacts
        // contacts = Contact.createContactsList(20);
        //   ertesitesekF= sqliteHelper.getSzabadsag(ered)
        val keres = sqliteHelper.ertesitesekSzabi(ered)


        // Create adapter passing in the sample user data
        val adapter = ErtesitesRecyclerActivity.ErtesitesRecycleAdapter(keres)
        // Attach the adapter to the recyclerview to populate items
        container.adapter = adapter
        // Set layout manager to position the items
        container.layoutManager = LinearLayoutManager(this)
        // That's all!

    }
}
package com.example.beosztasapp.activities


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.beosztasapp.R

class UsersListActivity : AppCompatActivity() {
    lateinit var contacts: ArrayList<szemelyek>
    var nev:String? = null
    var ered:Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid",-1)
        sqliteHelper = SqlHelper(this)

        // ...
        // Lookup the recyclerview in activity layout
        val beosztottak = findViewById<View>(R.id.recyclerViewUsers) as RecyclerView
        // Initialize contacts
       // contacts = Contact.createContactsList(20);
      //  contacts = sqliteHelper.getSzabadsag(ered)
        contacts = sqliteHelper.beosztottaim(ered)
        // Create adapter passing in the sample user data
        val adapter = UsersRecyclerAdapter(contacts)
        // Attach the adapter to the recyclerview to populate items
        beosztottak.adapter = adapter
        // Set layout manager to position the items
        beosztottak.layoutManager = LinearLayoutManager(this)
        // That's all!
    }
    /**
     * This method is to initialize views
     */

}
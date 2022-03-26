package com.example.beosztasapp.activities


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
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
        adapter?.setOnClickDeleteItem {
            deleteBeosztott(it.szemely_id)
        }
        // Set layout manager to position the items
        beosztottak.layoutManager = LinearLayoutManager(this)
        // That's all!


    }
   private fun deleteBeosztott(id:Int){
       val builder = AlertDialog.Builder(this)
       builder.setMessage("Biztos törölni akarja a beosztottat?")
       builder.setCancelable(true)
       builder.setPositiveButton("Igen"){dialog, _->
           sqliteHelper.deleteUser(id)

           dialog.dismiss()
       }
       builder.setNegativeButton("Nem"){dialog, _->
           dialog.dismiss()
       }
       val alert = builder.create()
       alert.show()
   }
}
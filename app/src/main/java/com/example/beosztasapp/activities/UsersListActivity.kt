package com.example.beosztasapp.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.beosztasapp.R

class UsersListActivity : AppCompatActivity() {
    lateinit var contacts: ArrayList<szemelyek>
    var nev: String? = null
    var ered: Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)
        sqliteHelper = SqlHelper(this)
        val beosztottak = findViewById<View>(R.id.recyclerViewUsers) as RecyclerView
        contacts = sqliteHelper.beosztottaim(ered)
        if(contacts.size== 0){
            Toast.makeText(this,"Nincsenek megjelenítendő alkalmazottak!", Toast.LENGTH_LONG).show()
        }
        val adapter = UsersRecyclerAdapter(contacts)
        beosztottak.adapter = adapter
        adapter?.setOnClickDeleteItem {
            deleteBeosztott(it.szemely_id)
        }
        beosztottak.layoutManager = LinearLayoutManager(this)


    }
    override fun onBackPressed() {
        // Toast.makeText(this, "back cklicked", Toast.LENGTH_LONG).show()

            //  Toast.makeText(this, szemely_id.toString() + nev+isFonok.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("szemelyid", ered)
            intent.putExtra("nev", nev)
            startActivity(intent)
        }

    private fun deleteBeosztott(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Biztos törölni akarja a beosztottat?")
        builder.setCancelable(true)
        builder.setPositiveButton("Igen") { dialog, _ ->
            sqliteHelper.deleteUser(id)
            val intent = Intent(this, UsersListActivity::class.java)
            intent.putExtra("szemelyid", ered)
            intent.putExtra("nev", nev)
            startActivity(intent)
            dialog.dismiss()
        }
        builder.setNegativeButton("Nem") { dialog, _ ->
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }
}
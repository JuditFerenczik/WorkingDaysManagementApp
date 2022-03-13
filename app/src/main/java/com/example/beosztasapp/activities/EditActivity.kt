package com.example.beosztasapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.beosztasapp.R
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*
import kotlin.collections.HashMap

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        btn_save.setOnClickListener{
            var fname: EditText= findViewById(R.id.eet_first_name)
            var lname: EditText= findViewById(R.id.eet_last_name)
            var email: EditText= findViewById(R.id.eet_email)
            var jelszo: EditText = findViewById(R.id.eet_password)
            var adoazon: EditText = findViewById(R.id.et_adoazon)
            var fonok: EditText = findViewById(R.id.et_boss)
            var munkarend: EditText = findViewById(R.id.et_munkarend)
            var szabad: EditText = findViewById(R.id.et_szabi)
            var hetim: EditText = findViewById(R.id.et_hetimunka)
            var nev:String = lname.text.toString() + " " + fname.text.toString()

            register_user(nev,email.text.toString(),jelszo.text.toString(),adoazon.text.toString(),fonok.text.toString(),munkarend.text.toString(),
                Date().toString(),szabad.text.toString(), hetim.text.toString())
        }


    }
    fun register_user(
        nev: String,
        email: String,
        jelszo: String,
        adoaz: String,
        fonok: String,
        munkarend: String,
        belepes: String,
        szabad: String,
        hetim:String
    ) {
        val request: StringRequest = object : StringRequest(
            Method.POST, "http://192.168.0.38/Webremek/app/insert.php",
            Response.Listener { response ->
                Toast.makeText(
                    applicationContext,
                    response,
                    Toast.LENGTH_SHORT
                ).show()
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    applicationContext,
                    error.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val map: MutableMap<String, String> = HashMap()
                map["nev"] = nev
                map["adoaz"] = adoaz
                map["fonok"] = fonok
                map["email"] = email
                map["jelszo"] = jelszo
                map["munkarend"] = munkarend
                map["belepes"] = belepes
                map["szabads"] = szabad
                map["hetimunk"] = hetim
                return map
            }
        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(request)
    }
}
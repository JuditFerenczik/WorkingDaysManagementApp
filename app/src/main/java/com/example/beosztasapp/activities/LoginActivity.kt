package com.example.beosztasapp.activites

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.beosztasapp.R
import com.example.beosztasapp.activities.CalendarActivity
import com.example.beosztasapp.activities.EditActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN

            )
        }
        tv_register.setOnClickListener{
            val intent = Intent(this@LoginActivity, CalendarActivity ::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener{
            var nev: EditText = findViewById(R.id.et_email)
            var jelszo: EditText = findViewById(R.id.et_password)
            register_u(nev.text.toString(),jelszo.text.toString())
        }

    }
    fun register_u(
        nev: String,
        jelszo: String
        ){
        val request: StringRequest = object : StringRequest(
            Method.POST, "http://192.168.0.38/Webremek/app/myinsert.php",
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
                map["jelszo"] = jelszo
                return map
            }

        }
        val queue = Volley.newRequestQueue(applicationContext)
        queue.add(request)
    }
}
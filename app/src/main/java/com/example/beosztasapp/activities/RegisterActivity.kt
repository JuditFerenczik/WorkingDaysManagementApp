package com.example.beosztasapp.activites


import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.example.beosztasapp.R
import com.google.gson.GsonBuilder
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import com.example.beosztasapp.activities.model
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*



class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        btn_register.setOnClickListener {
            var nev:EditText = findViewById(R.id.et_name)
            var fonok:EditText = findViewById(R.id.et_fonok)
            var email:EditText = findViewById(R.id.et_email)
            var jelszo:EditText = findViewById(R.id.et_password)
            var munkarend:EditText = findViewById(R.id.et_munkr)
            var eves:EditText = findViewById(R.id.et_eves)
            var hetimunka:EditText = findViewById(R.id.et_hetim)
            val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
            val network = BasicNetwork(HurlStack())

// Instantiate the RequestQueue with the cache and network. Start the queue.
            val requestQueue = RequestQueue(cache, network).apply {
                start()
            }
            val request =  StringRequest(
                Request.Method.GET , "http://192.168.0.38/Webremek/app/select.php",
                { response ->
                    print(response)
                    Toast.makeText(
                        applicationContext,
                        response,
                        Toast.LENGTH_SHORT
                    ).show()
                    val builder = GsonBuilder()
                    val gson = builder.create()
                    val data: List<model> = gson.fromJson(
                        response,
                        Array<model>::class.java
                    ).toList()
                    nev.setText(data[0].nev)
                    fonok.setText(data[0].fonok)
                    email.setText(data[0].email)
                    jelszo.setText(data[0].jelszo)
                    munkarend.setText(data[0].munkarend)
                    eves.setText(data[0].eves_szabadsag)
                    hetimunka.setText(data[0].heti_munkaido)
                },

                { error ->
                    Toast.makeText(
                        applicationContext,
                        error.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                })

         //   val queue2 = Volley.newRequestQueue(applicationContext)
            requestQueue.add(request)


        }

    }

}
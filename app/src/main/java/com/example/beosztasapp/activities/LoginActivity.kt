package com.example.beosztasapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.beosztasapp.R
import com.example.beosztasapp.activities.CalendarActivity
import com.example.beosztasapp.activities.HomeActivity
import com.example.beosztasapp.activities.HomeAlkActivity
import com.example.beosztasapp.activities.SqlHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sqliteHelper = SqlHelper(this)
        tv_register.setOnClickListener{
            val intent = Intent(this@LoginActivity, CalendarActivity ::class.java)
            startActivity(intent)
        }
        btn_login.setOnClickListener {
            val email = et_email.text.toString()
            val jelszo = et_password.text.toString()
            if(loginSzemelyek()){
                val ered = sqliteHelper.checkSzemely(email,jelszo)
                val nev = sqliteHelper.getSzemely(ered).nev
                val intent = Intent(this@LoginActivity, HomeAlkActivity ::class.java)
                intent.putExtra("szemelyid",ered)
                intent.putExtra("nev",nev)
                startActivity(intent)
            }

        }

    }
    private fun loginSzemelyek():Boolean{
        val email = et_email.text.toString()
        val jelszo = et_password.text.toString()
        if(email.isEmpty() ||jelszo.isEmpty()){
            Toast.makeText(this,"Töltse ki a kötelező mezőket!"+email +"-"+jelszo, Toast.LENGTH_SHORT).show()
            return false
        }else{
            val ered = sqliteHelper.checkSzemely(email,jelszo)
            if(ered > -1){
                val nev = sqliteHelper.getSzemely(ered).nev
                Toast.makeText(this,"Sikeres bejelentkezés: "+nev, Toast.LENGTH_LONG).show()

                clearEditText()
                return true
            }else{
                Toast.makeText(this,"E-mail cím vagy jelszó nem egyezik!",Toast.LENGTH_LONG).show()
                return  false
            }

        }
    }

    private fun clearEditText() {
        et_email.setText("")
        et_password.setText("")
    }

}
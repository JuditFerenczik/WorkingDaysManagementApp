package com.example.beosztasapp.activites


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.beosztasapp.R
import com.example.beosztasapp.activities.HomeActivity
import com.example.beosztasapp.activities.SqlHelper
import com.example.beosztasapp.activities.szemelyek
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.*


class RegisterActivity : AppCompatActivity() {
    var nev: String? = null
    var ered: Int = 0
    private lateinit var sqliteHelper: SqlHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)
        sqliteHelper = SqlHelper(this)
        val today = Calendar.getInstance()
        val now = today.timeInMillis
        et_belepes.minDate = now

        btn_register.setOnClickListener {


            if (validateRegisterDetails()) {
                val newSzemely = szemelyek()
                newSzemely.nev = et_name.text.toString()
                newSzemely.adoazonosito = et_ado.text.toString().toInt()
                newSzemely.fonok = ered
                var tmpmonth = ""
                var tmpday =""
               if (et_belepes.getDayOfMonth() < 10){
                   tmpday ="0"+ et_belepes.getDayOfMonth().toString()
               }else{
                   tmpday = et_belepes.getDayOfMonth().toString()
               }
                if((et_belepes.getMonth() + 1) <10){
                    tmpmonth = "0"+(et_belepes.getMonth() + 1).toString()
                }else{
                    tmpmonth =(et_belepes.getMonth() + 1).toString()
                }

                newSzemely.belepes = et_belepes.getYear()
                    .toString() + "." + tmpmonth + "." +  tmpday
                newSzemely.email = et_email.text.toString()
                newSzemely.munkarend = et_munkr.text.toString().toInt()
                newSzemely.eves_szabadsag = et_eves.text.toString().toInt()
                newSzemely.heti_munkaido = et_hetim.text.toString().toInt()

                sqliteHelper.addUser(newSzemely)
                Toast.makeText(this, "Új munkavállaló hozzáadva!", Toast.LENGTH_LONG).show()
                val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
                intent.putExtra("szemelyid", ered)
                intent.putExtra("nev", nev)
                intent.putExtra("isFonok", true)
                startActivity(intent)
            }
        }
    }

    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(et_name.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_name), true)
                false
            }
            et_name.text?.indexOf(" ")  == -1-> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_fullname), true)
                false
            }

            TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(et_ado.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_ado), true)
                false
            }
            et_ado.text.toString().length != 8 -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_ado_hibahossz), true)
                false
            }
            TextUtils.isEmpty(et_munkr.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_munk), true)
                false
            }

            et_munkr.text.toString().toInt() != 0 && et_munkr.text.toString().toInt() != 1 -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_munkareID), true)
                false
            }

            TextUtils.isEmpty(et_eves.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_eves), true)
                false
            }
            TextUtils.isEmpty(et_hetim.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_hetimunk), true)
                false
            }
            sqliteHelper.checkUser(et_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_already_entered), true)
                false
            }


            else -> {
                showErrorSnackBar(resources.getString(R.string.register_succesfull), false)
                true
            }


        }
    }

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar =
            Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.colorSnackBarError
                )
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@RegisterActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }


}



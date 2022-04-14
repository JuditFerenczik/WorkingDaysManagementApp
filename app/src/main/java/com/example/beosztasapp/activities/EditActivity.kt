package com.example.beosztasapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.beosztasapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    var nev: String? = null
    var ered: Int = 0
    private lateinit var sqliteHelper: SqlHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        nev = intent.getStringExtra("nev").toString()
        val firstName = nev!!.split(" ")[1]
        ered = intent.getIntExtra("szemelyid", -1)
        sqliteHelper = SqlHelper(this)

        btn_save.setOnClickListener {
            if (validateEditDetails()) {
                val tmpSzemely = sqliteHelper.getSzemely(ered)
                val isFonok = tmpSzemely.fonok == 0
                tmpSzemely.jelszo = eet_confirm_password.text.toString()
                sqliteHelper.updateUser(tmpSzemely)
                Toast.makeText(this, "A jelszó megváltoztatva!", Toast.LENGTH_LONG).show()
                if(isFonok){
                    val intent = Intent(this@EditActivity, HomeActivity::class.java)
                    intent.putExtra("szemelyid", ered)
                    intent.putExtra("nev", nev)
                    intent.putExtra("isFonok", true)
                    startActivity(intent)
                }else{
                    val intent = Intent(this@EditActivity, HomeAlkActivity::class.java)
                    intent.putExtra("szemelyid", ered)
                    intent.putExtra("nev", nev)
                    intent.putExtra("isFonok", false)
                    startActivity(intent)
                }

            }
        }
    }

    private fun validateEditDetails(): Boolean {
        return when {

            TextUtils.isEmpty(eet_email.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }
            TextUtils.isEmpty(eet_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }
            TextUtils.isEmpty(eet_confirm_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_enter_confirm_password),
                    true
                )
                false
            }
            eet_password.text.toString().trim { it <= ' ' } != eet_confirm_password.text.toString()
                .trim { it <= ' ' } -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_msg_password_and_confirm_password_mismatch),
                    true
                )
                false
            }

            sqliteHelper.getSzemely(ered).jelszo != eet_old_password.text.toString() || sqliteHelper.getSzemely(
                ered
            ).email != eet_email.text.toString() -> {
                showErrorSnackBar(
                    resources.getString(R.string.err_password_or_email_mismatch),
                    true
                )
                false
            }

            else -> {
                showErrorSnackBar(resources.getString(R.string.edit_succesfull), false)
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
                    this@EditActivity,
                    R.color.colorSnackBarError
                )
            )
        } else {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(
                    this@EditActivity,
                    R.color.colorSnackBarSuccess
                )
            )
        }
        snackBar.show()
    }

}
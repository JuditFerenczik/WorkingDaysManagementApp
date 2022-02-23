package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate : TextView? = null
    private var  tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById<Button>(R.id.mybutton)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener{
           clickDatePicker()
        }
    }

  private fun clickDatePicker(){
       val myCalendar = Calendar.getInstance()
       val year = myCalendar.get(Calendar.YEAR)
       val month = myCalendar.get(Calendar.MONTH)
       val day = myCalendar.get(Calendar.DAY_OF_WEEK)
      val dpd = DatePickerDialog(this,
          { _, selectedYear, selectedMonth, selectedDayOfMonth ->

              Toast.makeText(this, "Year was $selectedYear, month was ${selectedMonth +1}, day was $selectedDayOfMonth",Toast.LENGTH_LONG).show()
              val selectedDate = "$selectedDayOfMonth/${selectedMonth +1}/$selectedYear"
              tvSelectedDate?.text = selectedDate
              val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
              val theDate = sdf.parse(selectedDate)
              theDate?.let{
                  val selectedDateInMinutes = theDate.time/60000  //hour miliseconds
                  val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                  currentDate?.let {
                      val   currentDateInMinutes = currentDate.time/60000
                      val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                      tvAgeInMinutes?.text = differenceInMinutes.toString()
                  }

              }

          },
          year,
          month,
          day
      )
       dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
       dpd.show()



   }

}
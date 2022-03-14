package com.example.beosztasapp.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*
import kotlin.collections.ArrayList
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener








class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beosztasapp.R.layout.activity_calendar)
      //val calendarView: CalendarView = findViewById<View>(R.id.calendarView) as CalendarView

        val min: Calendar = Calendar.getInstance()
        val max: Calendar = Calendar.getInstance()
        min.set(2021,12,1)
        max.set(2022,12,31)

        calendarView.setMinimumDate(min)
        calendarView.setMaximumDate(max)
        val events: ArrayList<EventDay> = ArrayList()

        val listP = listOf(
            CalendarDay(
                Calendar.getInstance()
            ).apply {



                  //  Toast.makeText(this@CalendarActivity, Calendar.YEAR.toString(),Toast.LENGTH_LONG).show()

                    backgroundResource = com.example.beosztasapp.R.color.munkanap
                    // backgroundDrawable = [drawable]
                    labelColor = com.example.beosztasapp.R.color.munkanap_text

            }
        )
        val dropdown = findViewById<Spinner>(com.example.beosztasapp.R.id.spinner1)
        val items = arrayOf("Táppénz", "Szabadság")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.adapter = adapter
     //   val calendars: ArrayList<Calendar> = ArrayList()
        val calendar1 = Calendar.getInstance()
        var monthCount:Int = 1
        var currentdaysMonth:Int = 0
        for (j in 0..calendar1.getActualMaximum(Calendar.DAY_OF_YEAR)){
            val calendar2 = Calendar.getInstance()
            calendar2[Calendar.DAY_OF_YEAR] = 1
            calendar2.add(Calendar.DAY_OF_YEAR, j)
            currentdaysMonth++
            if(daysInMonth(monthCount, 2022) < currentdaysMonth){

               // currentdaysMonth -= daysInMonth(monthCount, 2022)
                Toast.makeText(this,daysInMonth(monthCount, 2022).toString()+",month:"+monthCount.toString()+ "days:" + currentdaysMonth,Toast.LENGTH_LONG ).show()
                monthCount++
                currentdaysMonth -= daysInMonth(monthCount, 2022)
            }
         //   for (i in 0..daysInMonth( Calendar.DAY_OF_MONTH, Calendar.YEAR)-1) {
               // val calendar2 = Calendar.getInstance()
             //   calendar2[Calendar.DAY_OF_MONTH] = 1
             //   calendar2.add(Calendar.DAY_OF_MONTH, i)
                val tmpDate: String = "2022." + monthCount.toString() + "." + currentdaysMonth.toString()
           //     val tmpDate: String = "2022." + (j+1).toString() + "." + (i+1).toString()


                /*  Toast.makeText(
                      this@CalendarActivity,
                      daysInMonth(Calendar.DAY_OF_MONTH, Calendar.YEAR).toString(),
                      Toast.LENGTH_SHORT
                  ).show()*/
                // Toast.makeText(this@CalendarActivity,tmpDate,Toast.LENGTH_SHORT).show()
                //calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                when {
                    isAthelyezett(tmpDate) ->{
                      //  Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        events.add(
                            EventDay(
                                calendar2,
                                com.example.beosztasapp.R.drawable.logowork,
                                Color.parseColor("#9C5700")
                            )
                        )
                    }
                    isMunkaszunet(tmpDate) -> {
                    //    Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        events.add(
                            EventDay(
                                calendar2,
                                com.example.beosztasapp.R.drawable.logopiheno,
                                Color.parseColor("#FFC7CE")
                            )
                        )
                    }
                    isPiheno(tmpDate) -> {
                      //  Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        events.add(
                            EventDay(
                                calendar2,
                                com.example.beosztasapp.R.drawable.logohetvege,
                                Color.parseColor("#FFC7CE")
                            )
                        )
                    }
                    calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ->{
                        events.add(
                            EventDay(
                                calendar2,
                                com.example.beosztasapp.R.drawable.logohetvege,
                                Color.parseColor("#9C5700")
                            )
                        )
                    }



                    else -> {  //if(isWorkday(tmpDate))
                        events.add(
                            EventDay(
                                calendar2,
                                com.example.beosztasapp.R.drawable.logowork,
                                Color.parseColor("#006100")
                            )
                        )
                    }
               // }

            }
        }

        // calendars.add(Calendar.DAY_OF_MONTH,1)
        calendarView.setEvents(events)

        //    calendarView.setCalendarDays(getSelectedDays())


        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
               // val clickedDayCalendar = eventDay.calendar
                val day:String = eventDay.calendar.get(5).toString()
                val year:String = eventDay.calendar.get(1).toString()
                val month:String = (eventDay.calendar.get(2)+1).toString()

                var clickedDayCalendar:String = year +"."+month+"."+day
                et_picked_date.setText(clickedDayCalendar)
                Toast.makeText(this@CalendarActivity,year,Toast.LENGTH_SHORT).show()
            }
        })

        /*
        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                val calendar = Calendar.getInstance()
                var year = 2021 + Calendar.YEAR
                var month = Calendar.MONTH +1
                var day = Calendar.DAY_OF_MONTH
                var allday = Calendar.DAY_OF_YEAR
                calendar.clear()
                var selectedOption:String =  spinner1.getSelectedItem().toString()
                var clickedDayCalendar = selectedOption+ allday.toString() + year.toString() +"."+month.toString()+"."+day.toString()
                et_picked_date.setText(clickedDayCalendar)
              //  calendarView.getSelectedDates()

                Toast.makeText(this@CalendarActivity,clickedDayCalendar,Toast.LENGTH_SHORT).show()
            }
        })

        val calendar = findViewById<CalendarView>(R.id.calendarView)
        calendar.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, i, il, i2 ->
            var date = "$i/$il/$i2"
            et_picked_date.setText(date)
            Toast.makeText(this@CalendarActivity,date,Toast.LENGTH_SHORT).show()

        })*/


    }
    open fun isWeekend(myDate:String):Boolean {
        val listWeekend = listOf("2022.3.5","2022.3.6","2022.3.12","2022.3.13","2022.3.19","2022.3.20","2022.3.26","2022.3.27","2022.10.26")
      //  Toast.makeText(this@CalendarActivity, myDate,Toast.LENGTH_LONG).show()
     //   Toast.makeText(this@CalendarActivity, listWeekend.contains(myDate).toString(),Toast.LENGTH_LONG).show()

        return listWeekend.contains(myDate)  && !isAthelyezett(myDate)

    /*       val d1 = Date(myDate)

        val c1 = Calendar.getInstance()
        c1.time = d1
        // System.out.println(c1[Calendar.DAY_OF_WEEK])

        if ((c1[Calendar.DAY_OF_WEEK] === Calendar.SATURDAY && !isAthelyezett(myDate))
            || c1[Calendar.DAY_OF_WEEK] === Calendar.SUNDAY
        ) {  //or sunday
            println("WEEKEND PRICE")
            return  true
        } else {
            println("WEEKDAY")
            return false
        }*/
    }
    open  fun  isWorkday(myDate: String):Boolean{
       // val d1 = Date(myDate)

      //  val c1 = Calendar.getInstance()
      //  c1.time = d1
        // System.out.println(c1[Calendar.DAY_OF_WEEK])

        return !isWeekend(myDate) && !isMunkaszunet(myDate)
    }
    open  fun  isAthelyezett(myDate: String):Boolean{
        val listAthelyezett = listOf("2022.3.26","2022.10.15")
       // val d1 = Date(myDate)

        // System.out.println(c1[Calendar.DAY_OF_WEEK])

        return listAthelyezett.contains(myDate)
    }

    open fun isMunkaszunet(myDate: String):Boolean{
        val listMunkaszunet = listOf("2022.1.1","2022.3.15", "2022.4.15","2022.4.18","2022.5.1","2022.6.6","2022.8.20","2022.10.23","2022.11.1","2022.12.25","2022.12.26")

    return listMunkaszunet.contains(myDate)
    }
    open fun isPiheno(myDate: String):Boolean{
        val listPiheno = listOf("2022.12.24","2022.10.31","2022.3.14")
    return listPiheno.contains(myDate)
    }

        private fun daysInMonth(month: Int, year: Int): Int {
            return when (month) {
                4, 6, 9, 11 -> 30
                2 -> if (leapYear(year)) 29 else 28
                else -> 31
            }
        }
        private fun leapYear(year: Int): Boolean {
            return when {
                year % 400 == 0 -> true
                year % 100 == 0 -> false
                else -> year % 4 == 0
            }
        }

    private fun getPiheno(): List<Calendar>? {
        val firstDisabled: Calendar = Calendar.getInstance()
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2)
        val secondDisabled: Calendar = Calendar.getInstance()
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1)
        val thirdDisabled: Calendar = Calendar.getInstance()
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18)
        val calendars: MutableList<Calendar> = ArrayList()
        calendars.add(firstDisabled)
        calendars.add(secondDisabled)
        calendars.add(thirdDisabled)
        return calendars
    }




}


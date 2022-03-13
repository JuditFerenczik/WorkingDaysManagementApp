package com.example.beosztasapp.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*
import com.applandeo.materialcalendarview.EventDay
import com.example.beosztasapp.R
import kotlin.collections.ArrayList


class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beosztasapp.R.layout.activity_calendar)
      //  val calendarView: CalendarView = findViewById<View>(R.id.calendarView) as CalendarView

        val min: Calendar = Calendar.getInstance()
        val max: Calendar = Calendar.getInstance()
        min.set(2022,1,1)
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
     //   val calendars: ArrayList<Calendar> = ArrayList()
        for (i in 0..daysInMonth( Calendar.DAY_OF_MONTH, Calendar.YEAR)-1) {
            val calendar2 = Calendar.getInstance()
            calendar2[Calendar.DAY_OF_MONTH] = 1
            calendar2.add(Calendar.DAY_OF_MONTH, i)
            val tmpDate: String = "2022." + (Calendar.MONTH + 1).toString() + "." + (i+1).toString()

            Toast.makeText(
                this@CalendarActivity,
                daysInMonth(Calendar.DAY_OF_MONTH, Calendar.YEAR).toString(),
                Toast.LENGTH_SHORT
            ).show()
            Toast.makeText(this@CalendarActivity,isAthelyezett(tmpDate).toString(),Toast.LENGTH_SHORT).show()
            when {
                isWeekend(tmpDate) ->{
                    events.add(
                        EventDay(
                            calendar2,
                            R.drawable.logohetvege,
                            Color.parseColor("#9C5700")
                        )
                    )
                }

                isAthelyezett(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            R.drawable.logowork,
                            Color.parseColor("#9C5700")
                        )
                    )
                }
                isMunkaszunet(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            R.drawable.logopiheno,
                            Color.parseColor("#FFC7CE")
                        )
                    )
                }
                isPiheno(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            R.drawable.logohetvege,
                            Color.parseColor("#FFC7CE")
                        )
                    )
                }
                else -> {  //if(isWorkday(tmpDate))
                    events.add(
                        EventDay(
                            calendar2,
                            R.drawable.logowork,
                            Color.parseColor("#006100")
                        )
                    )
                }
            }

        }
        // calendars.add(Calendar.DAY_OF_MONTH,1)
        calendarView.setEvents(events)

        //    calendarView.setCalendarDays(getSelectedDays())

        calendarView.setCalendarDays(listP)
    }
    open fun isWeekend(myDate:String):Boolean {
        val listWeekend = listOf("2022.3.5","2022.3.6","2022.3.12","2022.3.13","2022.3.19","2022.3.20","2022.3.26","2022.3.27","2022.10.26")
        Toast.makeText(this@CalendarActivity, myDate,Toast.LENGTH_LONG).show()
        Toast.makeText(this@CalendarActivity, listWeekend.contains(myDate).toString(),Toast.LENGTH_LONG).show()

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


package com.example.beosztasapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*



class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beosztasapp.R.layout.activity_calendar)

        val min: Calendar = Calendar.getInstance()
        val max: Calendar = Calendar.getInstance()
        min.set(2022,1,1)
        max.set(2022,12,31)

        calendarView.setMinimumDate(min)
        calendarView.setMaximumDate(max)
        val listP = listOf(
            CalendarDay(Calendar.getInstance()).apply {

            val cal = Calendar.getInstance()
            val month = cal[Calendar.MONTH]
            val year = cal[Calendar.YEAR]

            for (currentDay in 1..cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                cal[Calendar.DAY_OF_MONTH] = currentDay
                if(isPiheno(cal.toString())) {
                    //   selectedLabelColor = [color resource]
                    backgroundResource = com.example.beosztasapp.R.color.pihenonap
                    // backgroundDrawable = [drawable]
                    labelColor = com.example.beosztasapp.R.color.pihenonap_text
                    // selectedBackgroundResource = [drawable resource]
                    //  selectedBackgroundDrawable = [drawable]
                }
            }
            }
        )

        calendarView.setCalendarDays(listP)
    }
    open fun isWeekend(myDate:String):Boolean {

        val d1 = Date(myDate)

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
        }
    }
    open  fun  isWorkday(myDate: String):Boolean{
        val d1 = Date(myDate)

        val c1 = Calendar.getInstance()
        c1.time = d1
        // System.out.println(c1[Calendar.DAY_OF_WEEK])

        if (!isWeekend(myDate) && !isMunkaszunet(myDate)
        ) {  //or sunday
            println("WEEKEND PRICE")
            return  true
        } else {
            println("WEEKDAY")
            return false
        }
    }
    open  fun  isAthelyezett(myDate: String):Boolean{
        val listAthelyezett = listOf("2022.03.26","2022.10.15")
        val d1 = Date(myDate)

        // System.out.println(c1[Calendar.DAY_OF_WEEK])

        return listAthelyezett.contains(myDate)
    }

    open fun isMunkaszunet(myDate: String):Boolean{
        val listMunkaszunet = listOf("2022.01.01","2022.03.15", "2022.04.15","2022.04.18","2022.05.01","2022.06.06","2022.08.20","2022.10.23","2022.11.01","2022.12.25","2022.12.26")

    return listMunkaszunet.contains(myDate)
    }
    open fun isPiheno(myDate: String):Boolean{
        val listPiheno = listOf("2022.12.24","2022.10.31","2022.03.14")
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


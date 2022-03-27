package com.example.beosztasapp.activities

import android.content.Intent
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
    private lateinit var sqliteHelper: SqlHelper
    val szabList = ArrayList<String>()
    val tapList = ArrayList<String>()
    val egyebList = ArrayList<String>()
    val fuggoList = ArrayList<String>()
    var nev: String? = null
    var szemely_id: Int = 0
    var isFonok: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.beosztasapp.R.layout.activity_calendar)
        //val calendarView: CalendarView = findViewById<View>(R.id.calendarView) as CalendarView
        sqliteHelper = SqlHelper(this)
        szemely_id = intent.getIntExtra("szemelyid", -1)
        // Toast.makeText(applicationContext, "A szemely_id is "+ szemely_id, Toast.LENGTH_LONG).show()
        var szabmennyiseg: Int = sqliteHelper.checkSzabadsag(szemely_id)
        var tmpSzemely = sqliteHelper.getSzemely(szemely_id)
        nev = tmpSzemely.nev
        isFonok = tmpSzemely.fonok == 0

        tv_szabmennyiseg.setText("$szabmennyiseg/ ${tmpSzemely.eves_szabadsag}")
        val tmpKezdes = tmpSzemely.belepes
        val min: Calendar = Calendar.getInstance()
        val max: Calendar = Calendar.getInstance()
        var tmpYear = tmpKezdes.split(".")[0].toInt()
        var tmpMonth = tmpKezdes.split(".")[1].toInt()
        var tmpDay = tmpKezdes.split(".")[2].toInt()
        // Toast.makeText(this, "$tmpYear : $tmpMonth : $tmpDay",Toast.LENGTH_LONG).show()
        if (tmpYear < 2022 || (tmpYear == 2022 && tmpMonth == 1 && tmpDay == 1)) {
            min.set(2021, 11, 31) //excluded
            //  Toast.makeText(this, "$tmpYear : $tmpMonth : $tmpDay first",Toast.LENGTH_LONG).show()
        } else {
            if (tmpDay == 1) {
                tmpDay = daysInMonth(tmpMonth - 1, 2022)
                tmpMonth = tmpMonth - 2

            } else {
                tmpDay = tmpDay - 1
                tmpMonth = tmpMonth - 1
            }
            min.set(tmpYear, tmpMonth, tmpDay)
            //  Toast.makeText(this, "$tmpYear : $tmpMonth : $tmpDay second",Toast.LENGTH_LONG).show()
        }


        //min.set(2021,11,31) //excluded
        max.set(2022, 11, 31) //included

        calendarView.setMinimumDate(min)
        calendarView.setMaximumDate(max)
        val status = sqliteHelper.checkJelenletek(szemely_id)
        //   Toast.makeText(this,"Keresek status is "+ status, Toast.LENGTH_LONG).show()
        if (!status) {
            fillUpJelenletik(szemely_id)
            Toast.makeText(this, "Keresek feltöltve", Toast.LENGTH_LONG).show()
        }
        val keresList: ArrayList<Keresek> = sqliteHelper.getSzabadsag(szemely_id)
        keresList.forEach {
            //  Toast.makeText(this, it.datum, Toast.LENGTH_LONG).show()
            if (it.allapot == Allapot.ELINDITVA) {
                //      Toast.makeText(this, it.datum + "is fuggo", Toast.LENGTH_LONG).show()
                fuggoList.add(it.datum)
            } else if (it.statusz == StatuszK.SZABADSAG) {
                //      Toast.makeText(this, it.datum + " is szabad", Toast.LENGTH_LONG).show()
                szabList.add(it.datum)
            } else if (it.statusz == StatuszK.TAPPENZ) {
                //    Toast.makeText(this, it.datum + " is tappenz", Toast.LENGTH_LONG).show()
                tapList.add(it.datum)
            } else {
                egyebList.add(it.datum)
            }

        }
        /*
        fuggoList.forEach{
            Toast.makeText(this, "$it is fugi", Toast.LENGTH_LONG).show()
        }
        szabList.forEach{
            Toast.makeText(this, "$it is szabi", Toast.LENGTH_LONG).show()
        }
        tapList.forEach{
            Toast.makeText(this, "$it is tapi", Toast.LENGTH_LONG).show()
        }*/
        val events: ArrayList<EventDay> = ArrayList()

        val listP = listOf(
            CalendarDay(
                Calendar.getInstance()
            ).apply {

                backgroundResource = com.example.beosztasapp.R.color.munkanap
                // backgroundDrawable = [drawable]
                labelColor = com.example.beosztasapp.R.color.munkanap_text

            }
        )
        val dropdown = findViewById<Spinner>(com.example.beosztasapp.R.id.spinner1)
        val items = arrayOf("Táppénz", "Szabadság", "Egyéb")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dropdown.adapter = adapter
        //   val calendars: ArrayList<Calendar> = ArrayList()
        val calendar1 = Calendar.getInstance()
        var monthCount: Int = 1
        var currentdaysMonth: Int = 0
        for (j in 0..calendar1.getActualMaximum(Calendar.DAY_OF_YEAR)) {
            val calendar2 = Calendar.getInstance()
            calendar2[Calendar.DAY_OF_YEAR] = 1
            calendar2.add(Calendar.DAY_OF_YEAR, j)
            currentdaysMonth++
            if (daysInMonth(monthCount, 2022) < currentdaysMonth) {
                // currentdaysMonth -= daysInMonth(monthCount, 2022)
                //  Toast.makeText(this,daysInMonth(monthCount, 2022).toString()+",month:"+monthCount.toString()+ "days:" + currentdaysMonth,Toast.LENGTH_LONG ).show()
                currentdaysMonth -= daysInMonth(monthCount, 2022)
                monthCount++
            }
            val usedMonth: String = if (monthCount < 10) {
                "0$monthCount"
            } else {
                monthCount.toString()
            }
            val usedDays: String = if (currentdaysMonth < 10) {
                "0$currentdaysMonth"
            } else {
                currentdaysMonth.toString()
            }

            val tmpDate: String = "2022.$usedMonth.$usedDays"

            when {
                fuggoList.contains(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            com.example.beosztasapp.R.drawable.logo_fuggo,
                            Color.parseColor("#9C5700")
                        )
                    )
                }

                szabList.contains(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            com.example.beosztasapp.R.drawable.logo_szab,
                            Color.parseColor("#9C5700")
                        )
                    )
                }
                tapList.contains(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            com.example.beosztasapp.R.drawable.logo_beteg,
                            Color.parseColor("#9C5700")
                        )
                    )
                }
                egyebList.contains(tmpDate) -> {
                    events.add(
                        EventDay(
                            calendar2,
                            com.example.beosztasapp.R.drawable.logo_egyeb,
                            Color.parseColor("#9C5700")
                        )
                    )
                }
                isAthelyezett(tmpDate) -> {
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
                calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY -> {
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
        calendarView.setEvents(events)



        calendarView.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {
                // val clickedDayCalendar = eventDay.calendar
                val tmpday: Int = eventDay.calendar.get(5)
                val year: String = eventDay.calendar.get(1).toString()
                val tmpmonth: Int = (eventDay.calendar.get(2) + 1)
                val day: String
                if (tmpday < 10) {
                    day = "0$tmpday"
                } else {
                    day = tmpday.toString()
                }
                val month: String
                if (tmpmonth < 10) {
                    month = "0$tmpmonth"
                } else {
                    month = tmpmonth.toString()
                }

                val clickedDayCalendar = "$year.$month.$day"
                tv_pickedDate.setText(clickedDayCalendar)
                //  Toast.makeText(this@CalendarActivity,year,Toast.LENGTH_SHORT).show()
            }
        })
        btn_calendar_save.setOnClickListener {

            val calendar = Calendar.getInstance()
            val currentMonth = calendar.get(2) + 1
            val currentDay = calendar.get(5)


            val datum = tv_pickedDate.text.toString()
            var status = spinner1.getSelectedItem().toString()
            //   Toast.makeText(applicationContext, "A szemely_id is "+ szemely_id, Toast.LENGTH_LONG).show()
            status = if (status == "Táppénz") {
                "tappenz"
            } else if (status == "Szabadság") {
                "szabadsag"
            } else {
                "egyeb"
            }
            /*        Toast.makeText(
                    this@CalendarActivity,
                    "$datum: $status",

                    Toast.LENGTH_SHORT
                ).show()*/
            if (datum == "NaN") {
                Toast.makeText(
                    this@CalendarActivity,
                    "Nincs dátum kiválasztva!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (szabmennyiseg >= tmpSzemely.eves_szabadsag) {
                Toast.makeText(
                    this@CalendarActivity,
                    "Nem igényelhető több szabadság, az éves keret kimerült!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (currentMonth > (datum.split(".")[1]).toInt()) {
                Toast.makeText(
                    this@CalendarActivity,
                    "Múltbeli hónapra nem igényelhető szabadság!" + currentMonth.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (isMunkaszunet(datum) || isPiheno(datum)) {
                Toast.makeText(
                    this@CalendarActivity,
                    "Munkaszüneti/pihenő napra nem igényelhető szabadság!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (isWeekend(datum) && !isAthelyezett(datum)) {
                Toast.makeText(
                    this@CalendarActivity,
                    "Hétvégére nem igényelhető szabadság!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (sqliteHelper.checkAlreadyEnteredSzabi(szemely_id, datum)) {
                Toast.makeText(
                    this@CalendarActivity,
                    "Erre a napra már van szabadság igénylés!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, status + " kérelem elküldve!", Toast.LENGTH_LONG).show()
                sqliteHelper.addSzabiJelenletik(szemely_id, datum, status)

                if (isFonok) {
                    sqliteHelper.addSzabiKeresek(szemely_id, datum, status, "elfogadva")
                } else {
                    sqliteHelper.addSzabiKeresek(szemely_id, datum, status, "elinditva")
                }
                val intent = Intent(this, CalendarActivity::class.java)
                intent.putExtra("szemelyid", szemely_id)
                startActivity(intent)
            }

        }


    }

    override fun onBackPressed() {
        // Toast.makeText(this, "back cklicked", Toast.LENGTH_LONG).show()
        if (isFonok) {
            //  Toast.makeText(this, szemely_id.toString() + nev+isFonok.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("szemelyid", szemely_id)
            intent.putExtra("nev", nev)
            startActivity(intent)

        } else {
            // Toast.makeText(this, szemely_id.toString() + nev+isFonok.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeAlkActivity::class.java)
            intent.putExtra("szemelyid", szemely_id)
            intent.putExtra("nev", nev)
            startActivity(intent)

        }

    }


    fun isWeekend(datum: String): Boolean {
        val cal: Calendar = GregorianCalendar(
            datum.split(".")[0].toInt(),
            datum.split(".")[1].toInt() - 1,
            datum.split(".")[2].toInt()
        )
        val dayOfWeek = cal[Calendar.DAY_OF_WEEK]
        return dayOfWeek == 1 || dayOfWeek == 7
    }


    open fun fillUpJelenletik(szemelyID: Int) {

        for (i in 1..12) {
            for (j in 1..daysInMonth(i, 2022)) {
                val myMonthy: String
                val myDay: String
                if (i < 10) {
                    myMonthy = "0$i"
                } else {
                    myMonthy = i.toString()
                }
                if (j < 10) {
                    myDay = "0$j"
                } else {
                    myDay = j.toString()
                }
                val tmp = "2022.$myMonthy.$myDay"

                when {
                    isAthelyezett(tmp) -> {
                        //  Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        sqliteHelper.insertJelenletek(szemelyID, tmp, "ledolgozott")

                    }
                    isMunkaszunet(tmp) -> {
                        //    Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        sqliteHelper.insertJelenletek(szemelyID, tmp, "fiz_unnep")
                    }
                    isPiheno(tmp) -> {
                        //  Toast.makeText(this,tmpDate,Toast.LENGTH_LONG ).show()
                        sqliteHelper.insertJelenletek(szemelyID, tmp, "piheno")
                    }

                    isWeekend(tmp) -> {
                        // sqliteHelper.insertJelenletek(szemelyID,tmp, "hetvege")
                    }

                    else -> {  //if(isWorkday(tmpDate))
                        sqliteHelper.insertJelenletek(szemelyID, tmp, "ledolgozott")
                    }
                }
            }
        }
    }

    open fun isAthelyezett(myDate: String): Boolean {
        val listAthelyezett = listOf("2022.03.26", "2022.10.15")
        return listAthelyezett.contains(myDate)
    }

    open fun isMunkaszunet(myDate: String): Boolean {
        val listMunkaszunet = listOf(
            "2022.01.1",
            "2022.03.15",
            "2022.04.15",
            "2022.04.18",
            "2022.05.01",
            "2022.06.06",
            "2022.08.20",
            "2022.10.23",
            "2022.11.01",
            "2022.12.25",
            "2022.12.26"
        )

        return listMunkaszunet.contains(myDate)
    }

    open fun isPiheno(myDate: String): Boolean {
        val listPiheno = listOf("2022.12.24", "2022.10.31", "2022.03.14")
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


}


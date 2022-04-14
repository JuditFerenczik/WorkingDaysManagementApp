package com.example.beosztasapp.activities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "vizsgaremek.db"
        private const val DATABASE_VERSION = 3
        private const val TBL_SZEMELYEK = "szemelyek"
        private const val TBL_MUNKAREND = "munkarend"
        private const val TBL_KERESEK = "keresek"
        private const val TBL_JELENLETIK = "jelenletik"
        private const val SZEMELY_ID = "szemely_id"
        private const val NEV = "nev"
        private const val ADOAZONOSITO = "adoazonosito"
        private const val FONOK = "fonok"
        private const val MUNKARENDID = "munkarend_id"
        private const val MUNKAKOZI = "munkakozi_szunet"
        private const val KEZDES = "kezdes"
        private const val BEFEJEZES = "befejezes"
        private const val SZUNETKEZD = "szunet_kezd"
        private const val SZUNETVEG = "szunet_vege"
        private const val LEDOLGOZOTTORA = "ledolgozott_ora"
        private const val MUNKAREND = "munkarend"
        private const val BELEPES = "belepes"
        private const val EMAIL = "email"
        private const val JELSZO = "jelszo"
        private const val SZABADSAG = "eves_szabadsag"
        private const val HETI = "heti_munkaido"
        private const val DATUM = "datum"
        private const val STATUSZ = "statusz"
        private const val ALLAPOT = "allapot"
        private const val KERESEK_ID = "azon"


    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createszemelyek = ("CREATE TABLE IF NOT EXISTS " + TBL_SZEMELYEK + "(" +
                SZEMELY_ID + " INTEGER PRIMARY KEY, " + NEV + " TEXT, " +
                ADOAZONOSITO + " INTEGER, " + FONOK + " INTEGER, " +
                MUNKAREND + " INTEGER, " + BELEPES + " TEXT, " +
                EMAIL + " TEXT," + JELSZO + " TEXT," +
                SZABADSAG + " INTEGER, " + HETI + " INTEGER" + ")")
        val createKeresek = ("CREATE TABLE IF NOT EXISTS " + TBL_KERESEK + "(" +
                KERESEK_ID + " INTEGER PRIMARY KEY, " +
                SZEMELY_ID + " INTEGER, " + DATUM + " TEXT," +
                STATUSZ + " TEXT, " + ALLAPOT + " TEXT )")
        val createJelenletik = ("CREATE TABLE IF NOT EXISTS " + TBL_JELENLETIK + "(" +
                SZEMELY_ID + " INTEGER, " + DATUM + " TEXT," +
                STATUSZ + " TEXT, " + ALLAPOT + " TEXT )")
        val createmunkarend = ("CREATE TABLE IF NOT EXISTS " + TBL_MUNKAREND + "(" +  //PRIMARY KEY
                MUNKARENDID + " INTEGER , " + MUNKAKOZI + " INTEGER," +
                KEZDES + " TEXT," + BEFEJEZES + " TEXT," + SZUNETKEZD + " TEXT," +
                SZUNETVEG + " TEXT, " + LEDOLGOZOTTORA + " INTEGER )")

        db?.execSQL(createszemelyek)
        db?.execSQL(createKeresek)
        db?.execSQL(createJelenletik)
        db?.execSQL(createmunkarend)


        db?.execSQL("INSERT INTO " + TBL_SZEMELYEK + " (" + NEV + "," + ADOAZONOSITO + "," + FONOK + "," + MUNKAREND + "," + BELEPES + "," + EMAIL + "," + JELSZO + "," + SZABADSAG + "," + HETI + ") VALUES('Dotzi Pascal',234545632,0,0, '2022.01.02','dotzi@gmail.com', 'abc',30,35)")
        db?.execSQL("INSERT INTO " + TBL_SZEMELYEK + " (" + NEV + "," + ADOAZONOSITO + "," + FONOK + "," + MUNKAREND + "," + BELEPES + "," + EMAIL + "," + JELSZO + "," + SZABADSAG + "," + HETI + ")  VALUES('Ferenczik Judylein',234891632,1,1, '2022.02.04','judy@gmail.com', '123',24,40)")
        db?.execSQL("INSERT INTO " + TBL_SZEMELYEK + " (" + NEV + "," + ADOAZONOSITO + "," + FONOK + "," + MUNKAREND + "," + BELEPES + "," + EMAIL + "," + JELSZO + "," + SZABADSAG + "," + HETI + ")  VALUES('Pajkos Doro',235662432,4,1, '2022.02.03','doro@gmail.com', '456',22,40)")
        db?.execSQL("INSERT INTO " + TBL_SZEMELYEK + " (" + NEV + "," + ADOAZONOSITO + "," + FONOK + "," + MUNKAREND + "," + BELEPES + "," + EMAIL + "," + JELSZO + "," + SZABADSAG + "," + HETI + ")  VALUES('Kállai Imre',235566432,0,0, '2021.11.03','kallai@gmail.com', '123',26,40)")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(2,'2022.04.13','szabadsag','elfogadva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(1,'2022.02.25','szabadsag','elinditva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(1,'2022.03.07','tappenz','elfogadva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(1,'2022.03.08','tappenz','elinditva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(3,'2022.04.14','szabadsag','elinditva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(2,'2022.05.17','tappenz','elfogadva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(4,'2022.06.13','tappenz','elfogadva')")
        db?.execSQL("INSERT INTO " + TBL_KERESEK + " (" + SZEMELY_ID + ", " + DATUM + ", " + STATUSZ + ", " + ALLAPOT + ")  VALUES(4,'2022.09.16','tappenz','elfogadva')")
        db?.execSQL("INSERT INTO " + TBL_MUNKAREND + " (" + MUNKARENDID + ", " + MUNKAKOZI + ", " + KEZDES + ", " + BEFEJEZES + ", " + SZUNETKEZD + ", " + SZUNETVEG + ", " + LEDOLGOZOTTORA + ")   VALUES(1,30,'8:00','16:30','12:00', '12:30',8)")
        db?.execSQL("INSERT INTO " + TBL_MUNKAREND + " (" + MUNKARENDID + ", " + MUNKAKOZI + ", " + KEZDES + ", " + BEFEJEZES + ", " + SZUNETKEZD + ", " + SZUNETVEG + ", " + LEDOLGOZOTTORA + ")  VALUES(0,20,'8:00','16:20','12:00', '12:20',8)")
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_SZEMELYEK")
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_KERESEK")
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_JELENLETIK")
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_MUNKAREND")
        onCreate(db)
    }

    fun checkUser(email: String): Boolean {
        val columns = arrayOf(SZEMELY_ID)
        val db = this.readableDatabase
        val selection = "$EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(
            TBL_SZEMELYEK,
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null
        )  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkSzemely(email: String, password: String): Int {
        val columns = arrayOf(SZEMELY_ID)
        val db = this.readableDatabase
        val selection = "$EMAIL = ? AND $JELSZO = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TBL_SZEMELYEK, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null
        )
        val cursorCount = cursor.count
        if (cursor.moveToFirst()) {
            val indexID = cursor.getColumnIndex(SZEMELY_ID)
            val cursorValue = cursor.getInt(indexID)
            cursor.close()
            db.close()
            return cursorValue
        }
        cursor.close()
        db.close()

        return -1
    }

    fun ertesitesekF(fonokID: Int): ArrayList<ErtesitesF> {
        val resultList = ArrayList<ErtesitesF>()
        val db = this.readableDatabase
        val query =
            "SELECT   azon  ,datum, statusz,szemelyek.szemely_id,nev FROM szemelyek INNER JOIN keresek ON szemelyek.szemely_id = keresek.szemely_id WHERE allapot='elinditva' AND fonok =  " + fonokID.toString() + ";"

        db.use {
            val cursor = db.rawQuery(query, null)
            while (cursor.moveToNext()) {
                val ertesites = ErtesitesF(
                    keresek_id = cursor.getInt(0), mikor = cursor.getString(1),
                    tipus = cursor.getString(2), szemely_id = cursor.getInt(3),
                    nev = cursor.getString(4)
                )
                resultList.add(ertesites)
            }
            // cursor.close()
        }

        //  db.close
        return resultList
    }

    fun getAllUser(): ArrayList<szemelyek> {
        val columns = arrayOf(
            SZEMELY_ID, NEV, ADOAZONOSITO, FONOK, MUNKAREND, BELEPES, EMAIL, JELSZO,
            SZABADSAG, HETI
        )
        val sortOrder = "$NEV ASC"
        val szemelyList = ArrayList<szemelyek>()
        val db = this.readableDatabase
        val cursor = db.query(
            TBL_SZEMELYEK, //Table to query
            columns,            //columns to return
            null,     //columns for the WHERE clause
            null,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val szemely = szemelyek(
                    szemely_id = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            SZEMELY_ID
                        )
                    ).toInt(),
                    nev = cursor.getString(cursor.getColumnIndexOrThrow(NEV)),
                    adoazonosito = cursor.getInt(cursor.getColumnIndexOrThrow(ADOAZONOSITO)),
                    fonok = cursor.getInt(cursor.getColumnIndexOrThrow(FONOK)),
                    munkarend = cursor.getInt(cursor.getColumnIndexOrThrow(MUNKAREND)),
                    belepes = cursor.getString(cursor.getColumnIndexOrThrow(BELEPES)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)),
                    jelszo = cursor.getString(cursor.getColumnIndexOrThrow(JELSZO)),
                    eves_szabadsag = cursor.getInt(cursor.getColumnIndexOrThrow(SZABADSAG)),
                    heti_munkaido = cursor.getInt(cursor.getColumnIndexOrThrow(HETI))
                )
                szemelyList.add(szemely)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return szemelyList
    }

    fun getSzemely(szemelyID: Int): szemelyek {
        val columns = arrayOf(
            SZEMELY_ID, NEV, ADOAZONOSITO, FONOK, MUNKAREND, BELEPES, EMAIL, JELSZO,
            SZABADSAG, HETI
        )
        val sortOrder = "$NEV ASC"
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ?"
        val szemely = szemelyek()
        val selectionArgs = arrayOf(szemelyID.toString())
        val cursor = db.query(
            TBL_SZEMELYEK,
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {

            val szemely = szemelyek(
                szemely_id = cursor.getString(
                    cursor.getColumnIndexOrThrow(
                        SZEMELY_ID
                    )
                ).toInt(),
                nev = cursor.getString(cursor.getColumnIndexOrThrow(NEV)),
                adoazonosito = cursor.getInt(cursor.getColumnIndexOrThrow(ADOAZONOSITO)),
                fonok = cursor.getInt(cursor.getColumnIndexOrThrow(FONOK)),
                munkarend = cursor.getInt(cursor.getColumnIndexOrThrow(MUNKAREND)),
                belepes = cursor.getString(cursor.getColumnIndexOrThrow(BELEPES)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)),
                jelszo = cursor.getString(cursor.getColumnIndexOrThrow(JELSZO)),
                eves_szabadsag = cursor.getInt(cursor.getColumnIndexOrThrow(SZABADSAG)),
                heti_munkaido = cursor.getInt(cursor.getColumnIndexOrThrow(HETI))
            )
            return szemely

        }
        cursor.close()
        db.close()
        return szemely
    }


    fun getSzabadsag(szemelyID: Int): ArrayList<Keresek> {
        val columns = arrayOf(
            KERESEK_ID, SZEMELY_ID, DATUM, STATUSZ, ALLAPOT
        )
        val sortOrder = "$DATUM ASC"
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ?"
        val keresekList = ArrayList<Keresek>()
        val selectionArgs = arrayOf(szemelyID.toString())
        val cursor = db.query(
            TBL_KERESEK,
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val kids = cursor.getColumnIndex(KERESEK_ID)
                val ids = cursor.getColumnIndex(SZEMELY_ID)
                val currentDatum = cursor.getColumnIndex(DATUM)
                val currentStat = cursor.getColumnIndex(STATUSZ)
                var currentStatus: StatuszK
                var currentAllapot: Allapot
                if (cursor.getString(currentStat) == "szabadsag") {
                    currentStatus = StatuszK.SZABADSAG
                } else if (cursor.getString(currentStat) == "tappenz") {
                    currentStatus = StatuszK.TAPPENZ
                } else {
                    currentStatus = StatuszK.EGYEB
                }
                val currentAll = cursor.getColumnIndex(ALLAPOT)
                if (cursor.getString(currentAll) == "elinditva") {
                    currentAllapot = Allapot.ELINDITVA
                } else {
                    currentAllapot = Allapot.ELFOGADVA
                }

                val keresek = Keresek(
                    azon = cursor.getString(kids).toInt(),
                    szemely_id = cursor.getString(ids).toInt(),
                    datum = cursor.getString(currentDatum),
                    statusz = currentStatus,
                    allapot = currentAllapot
                )
                keresekList.add(keresek)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return keresekList
    }


    fun addUser(szemely: szemelyek) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NEV, szemely.nev)
        values.put(ADOAZONOSITO, szemely.adoazonosito)
        values.put(FONOK, szemely.fonok)
        values.put(MUNKAREND, szemely.munkarend)
        values.put(BELEPES, szemely.belepes)
        values.put(EMAIL, szemely.email)
        values.put(JELSZO, "Tesztjelszo")
        values.put(SZABADSAG, szemely.eves_szabadsag)
        values.put(HETI, szemely.heti_munkaido)
        db.insert(TBL_SZEMELYEK, null, values)
        db.close()
    }

    fun addSzabiJelenletik(szemelyID: Int, datum: String, status: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        val columns = arrayOf(szemelyID.toString(), datum)
        val selection = "$SZEMELY_ID = ? AND $DATUM = ?"
        val selectionArgs = arrayOf(szemelyID, datum)
        values.put(SZEMELY_ID, szemelyID)
        values.put(DATUM, datum)
        values.put(STATUSZ, status)
        db.update(
            TBL_JELENLETIK,
            values,
            "$SZEMELY_ID = ? AND $DATUM = ?",
            arrayOf(szemelyID.toString(), datum)
        )
        db.close()
    }

    fun addSzabiKeresek(szemelyID: Int, datum: String, status: String, allapot: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(SZEMELY_ID, szemelyID)
        values.put(DATUM, datum)
        values.put(STATUSZ, status)
        values.put(ALLAPOT, allapot)
        db.insert(TBL_KERESEK, null, values)
        db.close()

    }

    fun beosztottaim(szemelyID: Int): ArrayList<szemelyek> {
        val columns = arrayOf(
            SZEMELY_ID, NEV, ADOAZONOSITO, FONOK, MUNKAREND, BELEPES, EMAIL, JELSZO, SZABADSAG,
            HETI
        )
        val db = this.readableDatabase
        val selection = "$FONOK = ?"
        val beosztottList = ArrayList<szemelyek>()
        val selectionArgs = arrayOf(szemelyID.toString())
        val cursor = db.query(
            TBL_SZEMELYEK,
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            null
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {

                val szemely = szemelyek(
                    szemely_id = cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            SZEMELY_ID
                        )
                    ).toInt(),
                    nev = cursor.getString(cursor.getColumnIndexOrThrow(NEV)),
                    adoazonosito = cursor.getInt(cursor.getColumnIndexOrThrow(ADOAZONOSITO)),
                    fonok = cursor.getInt(cursor.getColumnIndexOrThrow(FONOK)),
                    munkarend = cursor.getInt(cursor.getColumnIndexOrThrow(MUNKAREND)),
                    belepes = cursor.getString(cursor.getColumnIndexOrThrow(BELEPES)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)),
                    jelszo = cursor.getString(cursor.getColumnIndexOrThrow(JELSZO)),
                    eves_szabadsag = cursor.getInt(cursor.getColumnIndexOrThrow(SZABADSAG)),
                    heti_munkaido = cursor.getInt(cursor.getColumnIndexOrThrow(HETI))
                )


                beosztottList.add(szemely)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return beosztottList
    }

    fun ertesitesek(szemelyID: Int): ArrayList<Keresek> {
        val columns = arrayOf(
            KERESEK_ID, SZEMELY_ID, DATUM, STATUSZ, ALLAPOT
        )

        val sortOrder = "$SZEMELY_ID ASC"
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ?  AND $ALLAPOT = ?"
        val ertesitesekList = ArrayList<Keresek>()
        val selectionArgs = arrayOf(szemelyID.toString(),"elinditva")
        val cursor = db.query(
            TBL_KERESEK,
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val kids = cursor.getColumnIndex(KERESEK_ID)
                val ids = cursor.getColumnIndex(SZEMELY_ID)
                val currentDatum = cursor.getColumnIndex(DATUM)
                val currentStat = cursor.getColumnIndex(STATUSZ)
                var currentStatus: StatuszK
                var currentAllapot: Allapot
                if (cursor.getString(currentStat) == "szabadsag") {
                    currentStatus = StatuszK.SZABADSAG
                } else if (cursor.getString(currentStat) == "tappenz") {
                    currentStatus = StatuszK.TAPPENZ
                } else {
                    currentStatus = StatuszK.EGYEB
                }
                val currentAll = cursor.getColumnIndex(ALLAPOT)
                if (cursor.getString(currentAll) == "elinditva") {
                    currentAllapot = Allapot.ELINDITVA
                } else {
                    currentAllapot = Allapot.ELFOGADVA
                }

                val keresek = Keresek(
                    azon = cursor.getString(kids).toInt(),
                    szemely_id = cursor.getString(ids).toInt(),
                    datum = cursor.getString(currentDatum),
                    statusz = currentStatus,
                    allapot = currentAllapot
                )
                ertesitesekList.add(keresek)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ertesitesekList
    }

    fun ertesitesekSzabi(szemelyID: Int): ArrayList<Keresek> {
        val columns = arrayOf(
            KERESEK_ID, SZEMELY_ID, DATUM, STATUSZ, ALLAPOT
        )

        val sortOrder = "$SZEMELY_ID ASC"
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ? AND $ALLAPOT = ?"
        val ertesitesekList = ArrayList<Keresek>()

        val selectionArgs = arrayOf(szemelyID.toString(), "elfogadva")

        val cursor = db.query(
            TBL_KERESEK,
            columns,            //columns to return
            selection,     //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,      //group the rows
            null,       //filter by row groups
            sortOrder
        )         //The sort order
        if (cursor.moveToFirst()) {
            do {
                val kids = cursor.getColumnIndex(KERESEK_ID)
                val ids = cursor.getColumnIndex(SZEMELY_ID)
                val currentDatum = cursor.getColumnIndex(DATUM)
                val currentStat = cursor.getColumnIndex(STATUSZ)
                var currentStatus: StatuszK
                var currentAllapot: Allapot
                if (cursor.getString(currentStat) == "szabadsag") {
                    currentStatus = StatuszK.SZABADSAG
                } else if (cursor.getString(currentStat) == "tappenz") {
                    currentStatus = StatuszK.TAPPENZ
                } else {
                    currentStatus = StatuszK.EGYEB
                }
                val currentAll = cursor.getColumnIndex(ALLAPOT)
                if (cursor.getString(currentAll) == "elinditva") {
                    currentAllapot = Allapot.ELINDITVA
                } else {
                    currentAllapot = Allapot.ELFOGADVA
                }

                val keresek = Keresek(
                    azon = cursor.getString(kids).toInt(),
                    szemely_id = cursor.getString(ids).toInt(),
                    datum = cursor.getString(currentDatum),
                    statusz = currentStatus,
                    allapot = currentAllapot
                )
                ertesitesekList.add(keresek)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ertesitesekList
    }


    fun checkAlreadyEnteredSzabi(szemelyID: Int, datum: String): Boolean {

        val db = this.readableDatabase
        val columns = arrayOf(SZEMELY_ID)
        val selection = "$SZEMELY_ID = ? AND $DATUM = ?"
        val selectionArgs = arrayOf(szemelyID.toString(), datum)

        val cursor = db.query(
            TBL_KERESEK,
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null
        )  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkJelenletek(szemelyID: Int): Boolean {
        val columns = arrayOf(DATUM)
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ?"
        val selectionArgs = arrayOf(szemelyID.toString())
        val cursor = db.query(
            TBL_JELENLETIK,
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null
        )  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false
    }

    fun checkSzabadsag(szemelyID: Int): Int {
        val columns = arrayOf(SZEMELY_ID)
        val db = this.readableDatabase
        val selection = "$SZEMELY_ID = ? AND $STATUSZ =?"
        val selectionArgs = arrayOf(szemelyID.toString(), "szabadsag")
        val cursor = db.query(
            TBL_KERESEK,
            columns,        //columns to return
            selection,      //columns for the WHERE clause
            selectionArgs,  //The values for the WHERE clause
            null,  //group the rows
            null,   //filter by row groups
            null
        )  //The sort order
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return cursorCount

    }

    fun insertJelenletek(szemelyID: Int, datum: String, status: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(SZEMELY_ID, szemelyID)
        values.put(DATUM, datum)
        values.put(STATUSZ, status)
        // Inserting Row
        db.insert(TBL_JELENLETIK, null, values)
        db.close()
    }

    fun insertMunkarend(
        munkakozi: Int,
        kezdes: String,
        befejezes: String,
        SZkezdes: String,
        SZbefejezes: String,
        ledolgozott: Int
    ) {
        val db = this.writableDatabase  // will need an munkarend_id
        val values = ContentValues()
        values.put(MUNKAKOZI, munkakozi)
        values.put(KEZDES, kezdes)
        values.put(BEFEJEZES, befejezes)
        values.put(SZUNETKEZD, SZkezdes)
        values.put(LEDOLGOZOTTORA, ledolgozott)
        // Inserting Row
        db.insert(TBL_MUNKAREND, null, values)
        db.close()
    }

    fun updateUser(szemely: szemelyek) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NEV, szemely.nev)
        values.put(EMAIL, szemely.email)
        values.put(JELSZO, szemely.jelszo)
        db.update(
            TBL_SZEMELYEK, values, "$SZEMELY_ID = ?",
            arrayOf(szemely.szemely_id.toString())
        )
        db.close()
    }

    fun elfogadSzabi(keresekID: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ALLAPOT, "elfogadva")
        db.update(
            TBL_KERESEK, values, "$KERESEK_ID = ?",
            arrayOf(keresekID.toString())
        )
        db.close()
    }

    fun elutasitSzabi(keresID: Int) {
        val db = this.writableDatabase
        db.delete(
            TBL_KERESEK, "$KERESEK_ID = ?",
            arrayOf(keresID.toString())
        )

        db.close()
    }

    fun deleteUser(szemelyID: Int) {
        val db = this.writableDatabase
        db.delete(
            TBL_SZEMELYEK, "$SZEMELY_ID = ?",
            arrayOf(szemelyID.toString())
        )
        db.delete(
            TBL_KERESEK, "$SZEMELY_ID = ?",
            arrayOf(szemelyID.toString())
        )
        db.delete(
            TBL_JELENLETIK, "$SZEMELY_ID = ?",
            arrayOf(szemelyID.toString())
        )
        db.close()
    }
}
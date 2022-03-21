package com.example.beosztasapp.activities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION)
    {
        companion object{
            private const val DATABASE_NAME="vizsgaremek.db"
            private const val DATABASE_VERSION= 2
            private const val TBL_SZEMELYEK ="szemelyek"
            private const val TBL_MUNKAREND ="munkarend"
            private  const val TBL_KERESEK = "keresek"
            private  const val TBL_JELENLETIK= "jelenletik"
            private const val SZEMELY_ID =  "szemely_id"
            private const val NEV = "nev"
            private const val ADOAZONOSITO ="adoazonosito"
            private const val FONOK ="fonok"
            private const val MUNKARENDID ="munkarend_id"
            private const val MUNKAKOZI ="munkakozi_szunet"
            private const val KEZDES = "kezdes"
            private const val BEFEJEZES ="befejezes"
            private const val SZUNETKEZD ="szunet_kezd"
            private const val SZUNETVEG = "szunet_vege"
            private const val LEDOLGOZOTTORA = "ledolgozott_ora"
            private const val MUNKAREND ="munkarend"
            private const val BELEPES = "belepes"
            private const val EMAIL = "email"
            private const val JELSZO ="jelszo"
            private const val SZABADSAG = "eves_szabadsag"
            private const val HETI = "heti_munkaido"
            private const val  DATUM ="datum"
            private  const val TAPPENZ="tappenz"
            private  const val SZABAD ="szabadsag"
            private  const val ELINDITVA ="elinditva"
            private  const val ELFOGADVA ="elfogadva"
            private  const val LEDOLGOZOTT ="ledolgozott"
            private  const val FIZ ="fiz_unnep"
            private  const val PIHENO ="piheno"
            private  const val  STATUSZ ="statusz"
            private  const val ALLAPOT ="allapot"


        }

        override fun onCreate(db: SQLiteDatabase?) {
            val createszemelyek = ("CREATE TABLE IF NOT EXISTS " + TBL_SZEMELYEK + "(" +
                    SZEMELY_ID + " INTEGER PRIMARY KEY, " +  NEV + " TEXT, "+
                    ADOAZONOSITO + " INTEGER, " + FONOK + " INTEGER, " +
            MUNKAREND +" INTEGER, " + BELEPES + " TEXT, " +
                    EMAIL +" TEXT," + JELSZO + " TEXT,"+
                    SZABADSAG + " INTEGER, "+  HETI + " INTEGER" + ")" )
            val createKeresek =("CREATE TABLE IF NOT EXISTS " + TBL_KERESEK + "(" +
                    SZEMELY_ID + " INTEGER, " + DATUM + " TEXT,"+
                    STATUSZ +" TEXT, " + ALLAPOT + " TEXT )")
            val createJelenletik =("CREATE TABLE IF NOT EXISTS " + TBL_JELENLETIK + "(" +
                    SZEMELY_ID + " INTEGER, " + DATUM + " TEXT,"+
                    STATUSZ +" TEXT, " + ALLAPOT + " TEXT )")
            val createmunkarend =("CREATE TABLE IF NOT EXISTS " + TBL_MUNKAREND + "(" +
                    MUNKARENDID+ " INTEGER, " + MUNKAKOZI + " INTEGER," +
                    KEZDES+ " TEXT,"+ BEFEJEZES + " TEXT,"+ SZUNETKEZD +" TEXT,"+
            SZUNETVEG +" TEXT, " + LEDOLGOZOTTORA + " INTEGER )")

            db?.execSQL(createszemelyek)
            db?.execSQL(createKeresek)
            db?.execSQL(createJelenletik)
            db?.execSQL(createmunkarend)
           // db?.execSQL("insert into" + TBL_SZEMELYEK  +"(" + NEV +"," + ADOAZONOSITO +","+ FONOK +","+ MUNKAREND +","+ BELEPES +","+ EMAIL+","+ JELSZO +","+ SZABADSAG +","+ HETI +") values('Dotzi',234545632,'Kallai Imre',1, '2022.1.2','dotzi@gmail.com', 'abc',30,35)")
            db?.execSQL("insert into " + TBL_SZEMELYEK + " (" + NEV +"," + ADOAZONOSITO +","+ FONOK +","+ MUNKAREND +","+ BELEPES +","+ EMAIL+","+ JELSZO +","+ SZABADSAG +","+ HETI +") values('Ferenczik Judylein',234891632,4,1, '2022.2.4','judy@gmail.com', '123',24,40)")
            db?.execSQL("insert into szemelyek(nev, adoazonosito,fonok, munkarend, belepes, email, jelszo, eves_szabadsag, heti_munkaido) values('Dotzi Pascal',234545632,4,1, '2022.1.2','dotzi@gmail.com', 'abc',30,35)")
            db?.execSQL("insert into szemelyek(nev, adoazonosito,fonok, munkarend, belepes, email, jelszo, eves_szabadsag, heti_munkaido) values('Pajkos Doro',235662432,4,1, '2022.2.3','doro@gmail.com', '456',22,40)")
            db?.execSQL("insert into szemelyek(nev, adoazonosito,fonok, munkarend, belepes, email, jelszo, eves_szabadsag, heti_munkaido) values('Kállai Imre',235566432,0,0, '2021.11.3','kallai@gmail.com', '123',26,40)")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(2,'2022.04.13','szabasag','elfogadva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(1,'2022.02.25','szabasag','elinditva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(1,'2022.03.07','tappenz','elfogadva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(1,'2022.03.08','tappenz','elinditva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(3,'2022.04.14','szabasag','elinditva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(2,'2022.05.17','tappenz','elfogadva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(4,'2022.06.13','tappenz','elfogadva')")
            db?.execSQL("insert into keresek(szemely_id ,datum, statusz, allapot) values(4,'2022.09.16','tappenz','elfogadva')")
            db?.execSQL("insert into munkarend(munkarend_id,munkakozi_szunet,kezdes, befejezes, szunet_kezd, szunet_vege, ledolgozott_ora) values(1,30,'8:00','16:30','12:00', '12:30',8)")
            db?.execSQL("insert into munkarend(munkarend_id,munkakozi_szunet,kezdes, befejezes, szunet_kezd, szunet_vege, ledolgozott_ora) values(0,20,'8:00','16:20','12:00', '12:20',8)")
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $TBL_SZEMELYEK")
            db!!.execSQL("DROP TABLE IF EXISTS $TBL_KERESEK")
            db!!.execSQL("DROP TABLE IF EXISTS $TBL_JELENLETIK")
            db!!.execSQL("DROP TABLE IF EXISTS $TBL_MUNKAREND")
            onCreate(db)
        }
        fun checkUser(email: String): Boolean {
            // array of columns to fetch
            val columns = arrayOf(SZEMELY_ID)
            val db = this.readableDatabase
            // selection criteria
            val selection = "$EMAIL = ?"
            // selection argument
            val selectionArgs = arrayOf(email)
            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            val cursor = db.query(
                TBL_SZEMELYEK, //Table to query
                columns,        //columns to return
                selection,      //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,  //group the rows
                null,   //filter by row groups
                null)  //The sort order
            val cursorCount = cursor.count
            cursor.close()
            db.close()
            if (cursorCount > 0) {
                return true
            }
            return false
        }
        /**
         * This method to check user exist or not
         *
         * @param email
         * @param password
         * @return true/false
         */
        fun checkSzemely(email: String, password: String): Int {
            // array of columns to fetch
            val columns = arrayOf(SZEMELY_ID)
            val db = this.readableDatabase
            // selection criteria
            val selection = "$EMAIL = ? AND $JELSZO = ?"
            // selection arguments
            val selectionArgs = arrayOf(email, password)
            // query user table with conditions
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
             */
            val cursor = db.query(
                TBL_SZEMELYEK, //Table to query
                columns, //columns to return
                selection, //columns for the WHERE clause
                selectionArgs, //The values for the WHERE clause
                null,  //group the rows
                null, //filter by row groups
                null) //The sort order

            val cursorCount = cursor.count
            if (cursor.moveToFirst()) {
                val indexID =  cursor.getColumnIndex(SZEMELY_ID)
                val cursorValue =  cursor.getInt(indexID)
                cursor.close()
                db.close()
                return cursorValue
            }
            cursor.close()
            db.close()

            return -1
        }

        /**
         * This method is to fetch all user and return the list of user records
         *
         * @return list
         */
        fun getAllUser(): ArrayList<szemelyek> {
            // array of columns to fetch
            val columns = arrayOf(
                SZEMELY_ID, NEV, ADOAZONOSITO, FONOK, MUNKAREND, BELEPES, EMAIL,JELSZO,
                SZABADSAG, HETI)
            // sorting orders
            val sortOrder = "$NEV ASC"
            val szemelyList = ArrayList<szemelyek>()
            val db = this.readableDatabase
            // query the user table
            val cursor = db.query(
                TBL_SZEMELYEK, //Table to query
                columns,            //columns to return
                null,     //columns for the WHERE clause
                null,  //The values for the WHERE clause
                null,      //group the rows
                null,       //filter by row groups
                sortOrder)         //The sort order
            if (cursor.moveToFirst()) {
                do {
                    val szemely = szemelyek(szemely_id = cursor.getString(cursor.getColumnIndexOrThrow(
                        SZEMELY_ID)).toInt(),
                        nev = cursor.getString(cursor.getColumnIndexOrThrow(NEV)),
                        adoazonosito = cursor.getInt(cursor.getColumnIndexOrThrow(ADOAZONOSITO)),
                        fonok = cursor.getInt(cursor.getColumnIndexOrThrow(FONOK)),
                        munkarend = cursor.getInt(cursor.getColumnIndexOrThrow(MUNKAREND)),
                        belepes = cursor.getString(cursor.getColumnIndexOrThrow(BELEPES)),
                        email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)),
                        jelszo = cursor.getString(cursor.getColumnIndexOrThrow(JELSZO)),
                        eves_szabadsag = cursor.getInt(cursor.getColumnIndexOrThrow(SZABADSAG)),
                        heti_munkaido = cursor.getInt(cursor.getColumnIndexOrThrow(HETI)))
                        szemelyList.add(szemely)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return szemelyList
        }
        fun getSzemely(szemelyID:Int): szemelyek {
            // array of columns to fetch
            val columns = arrayOf(
                SZEMELY_ID, NEV, ADOAZONOSITO, FONOK, MUNKAREND, BELEPES, EMAIL,JELSZO,
                SZABADSAG, HETI)
            // sorting orders
            val sortOrder = "$NEV ASC"
            val db = this.readableDatabase
            val selection = "$SZEMELY_ID = ?"
            val szemely = szemelyek()
            // selection arguments
            val selectionArgs = arrayOf(szemelyID.toString())
            // query user table with conditions
            // query the user table
            val cursor = db.query(
                TBL_SZEMELYEK, //Table to query
                columns,            //columns to return
                selection ,     //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,      //group the rows
                null,       //filter by row groups
                sortOrder)         //The sort order
            if (cursor.moveToFirst()) {

                val szemely = szemelyek(szemely_id = cursor.getString(cursor.getColumnIndexOrThrow(
                    SZEMELY_ID)).toInt(),
                    nev = cursor.getString(cursor.getColumnIndexOrThrow(NEV)),
                    adoazonosito = cursor.getInt(cursor.getColumnIndexOrThrow(ADOAZONOSITO)),
                    fonok = cursor.getInt(cursor.getColumnIndexOrThrow(FONOK)),
                    munkarend = cursor.getInt(cursor.getColumnIndexOrThrow(MUNKAREND)),
                    belepes = cursor.getString(cursor.getColumnIndexOrThrow(BELEPES)),
                    email = cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)),
                    jelszo = cursor.getString(cursor.getColumnIndexOrThrow(JELSZO)),
                    eves_szabadsag = cursor.getInt(cursor.getColumnIndexOrThrow(SZABADSAG)),
                    heti_munkaido = cursor.getInt(cursor.getColumnIndexOrThrow(HETI)))
               return szemely

            }
            cursor.close()
            db.close()
            return szemely
        }


       fun getSzabadsag(szemelyID: Int):ArrayList<Keresek>{
           val columns = arrayOf(
               SZEMELY_ID, DATUM, STATUSZ, ALLAPOT)
           // sorting orders
           val sortOrder = "$DATUM ASC"
           val db = this.readableDatabase
           val selection = "$SZEMELY_ID = ?"
           val keresekList = ArrayList<Keresek>()
           // selection arguments
           val selectionArgs = arrayOf(szemelyID.toString())
           // query user table with conditions
           // query the user table
           val cursor = db.query(
               TBL_KERESEK, //Table to query
               columns,            //columns to return
               selection ,     //columns for the WHERE clause
               selectionArgs,  //The values for the WHERE clause
               null,      //group the rows
               null,       //filter by row groups
               sortOrder)         //The sort order
           if (cursor.moveToFirst()) {
               do {

                   val ids = cursor.getColumnIndex(SZEMELY_ID)
                   val currentDatum = cursor.getColumnIndex(DATUM)
                   val currentStat = cursor.getColumnIndex(STATUSZ)
                   var currentStatus: StatuszK
                   var currentAllapot: Allapot
                   if (cursor.getString(currentStat) == "szabadsag") {
                       currentStatus = StatuszK.SZABADSAG
                   } else {
                       currentStatus = StatuszK.TAPPENZ
                   }
                   val currentAll = cursor.getColumnIndex(ALLAPOT)
                   if (cursor.getString(currentAll) == "elinditva") {
                       currentAllapot = Allapot.ELINDITVA
                   } else {
                       currentAllapot = Allapot.ELFOGADVA
                   }

                   val keresek = Keresek(
                       szemely_id = cursor.getString(ids).toInt(),
                       datum = cursor.getString(currentDatum),
                       statusz = currentStatus,
                       allapot = currentAllapot
                   )
                   keresekList.add(keresek)
               }while (cursor.moveToNext())
           }
           cursor.close()
           db.close()
           return keresekList
       }


        /**
         * This method is to create user record
         *
         * @param user
         */
        fun addUser(szemely: szemelyek) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(NEV, szemely.nev)
            values.put(EMAIL, szemely.email)
            values.put(JELSZO, szemely.jelszo)
            // Inserting Row
            db.insert(TBL_SZEMELYEK, null, values)
            db.close()
        }
        fun addSzabiJelenletik(szemelyID: Int,datum: String,status: String) {
            val db = this.writableDatabase
            val values = ContentValues()
            val columns = arrayOf(szemelyID.toString(),datum)
            val selection = "$SZEMELY_ID = ? AND $DATUM = ?"
            val selectionArgs = arrayOf(szemelyID, datum)
            values.put(SZEMELY_ID, szemelyID)
            values.put(DATUM, datum)
            values.put(STATUSZ,status)
            // Inserting Row
            db.update(TBL_JELENLETIK,  values,"$SZEMELY_ID = ? AND $DATUM = ?",arrayOf(szemelyID.toString(),datum))
            db.close()
        }
        fun addSzabiKeresek(szemelyID: Int,datum: String,status: String, allapot: String) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(SZEMELY_ID, szemelyID)
            values.put(DATUM, datum)
            values.put(STATUSZ,status)
            values.put(ALLAPOT,allapot)
            // Inserting Row
            db.insert(TBL_KERESEK,null, values)
            db.close()

        }
        fun checkAlreadyEnteredSzabi(szemelyID: Int, datum:String):Boolean{

            val db = this.readableDatabase
            val columns = arrayOf(SZEMELY_ID)
            val selection = "$SZEMELY_ID = ? AND $DATUM = ?"
            val selectionArgs = arrayOf(szemelyID.toString(), datum)
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            val cursor = db.query(
                TBL_KERESEK, //Table to query
                columns,        //columns to return
                selection,      //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,  //group the rows
                null,   //filter by row groups
                null)  //The sort order
            val cursorCount = cursor.count
            cursor.close()
            db.close()
            if (cursorCount > 0) {
                return true
            }
            return false
        }
        fun checkJelenletek(szemelyID: Int):Boolean{
            val columns = arrayOf(DATUM)
            val db = this.readableDatabase
            // selection criteria
            val selection = "$SZEMELY_ID = ?"
            // selection argument
            val selectionArgs = arrayOf(szemelyID.toString())
            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            val cursor = db.query(
                TBL_JELENLETIK, //Table to query
                columns,        //columns to return
                selection,      //columns for the WHERE clause
                selectionArgs,  //The values for the WHERE clause
                null,  //group the rows
                null,   //filter by row groups
                null)  //The sort order
            val cursorCount = cursor.count
            cursor.close()
            db.close()
            if (cursorCount > 0) {
                return true
            }
            return false
        }
      fun checkSzabadsag(szemelyID: Int):Int{
          val columns = arrayOf(SZEMELY_ID)
          val db = this.readableDatabase
          val selection = "$SZEMELY_ID = ? AND $STATUSZ =?"
          val selectionArgs = arrayOf(szemelyID.toString(), "szabadsag")
          val cursor = db.query(
              TBL_KERESEK, //Table to query
              columns,        //columns to return
              selection,      //columns for the WHERE clause
              selectionArgs,  //The values for the WHERE clause
              null,  //group the rows
              null,   //filter by row groups
              null)  //The sort order
          val cursorCount = cursor.count
          cursor.close()
          db.close()
          return cursorCount

      }
        fun insertJelenletek(szemelyID: Int, datum:String, status:String){
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(SZEMELY_ID,szemelyID)
            values.put(DATUM, datum)
            values.put(STATUSZ, status)
            // Inserting Row
            db.insert(TBL_JELENLETIK, null, values)
            db.close()
        }
        /**
         * This method to update user record
         *
         * @param user
         */
        fun updateUser(szemely: szemelyek) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(NEV, szemely.nev)
            values.put(EMAIL, szemely.email)
            values.put(JELSZO, szemely.jelszo)
            // updating row
            db.update(
                TBL_SZEMELYEK, values, "$SZEMELY_ID = ?",
                arrayOf(szemely.szemely_id.toString()))
            db.close()
        }
        /**
         * This method is to delete user record
         *
         * @param user
         */
        fun deleteUser(szemely: szemelyek) {
            val db = this.writableDatabase
            // delete user record by id
            db.delete(
                TBL_SZEMELYEK, "$SZEMELY_ID = ?",
                arrayOf(szemely.szemely_id.toString()))
            db.close()
        }
    }
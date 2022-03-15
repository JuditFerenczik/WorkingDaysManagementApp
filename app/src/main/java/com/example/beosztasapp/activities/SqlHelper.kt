package com.example.beosztasapp.activities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null, DATABASE_VERSION)
    {
        companion object{
            private const val DATABASE_NAME="vizsgaremek.db"
            private const val DATABASE_VERSION= 1
            private const val TBL_SZEMELYEK ="szemelyek"
            private const val TBL_MUNKAREND ="munkarend"
            private const val SZEMELY_ID =  "szemely_id"
            private const val NEV = "nev"
            private const val ADOAZONOSITO ="adoazonosito"
            private const val FONOK ="fonok"
            private const val MUNKAREND ="munkarend"
            private const val BELEPES = "belepes"
            private const val EMAIL = "email"
            private const val JELSZO ="jelszo"
            private const val SZABADSAG = "eves_szabadsag"
            private const val HETI = "heti_munkaido"

        }

        override fun onCreate(db: SQLiteDatabase?) {
            val createszemelyek = ("CREATE TABLE " + TBL_SZEMELYEK + "(" +
                    SZEMELY_ID + " INTEGER PRIMARY KEY, " +  NEV + " TEXT, "+
                    ADOAZONOSITO + " INTEGER, " + FONOK + " TEXT, " +
            MUNKAREND +" INTEGER, " + BELEPES + " TEXT, " +
                    EMAIL +" TEXT," + JELSZO + " TEXT,"+
                    SZABADSAG + " INTEGER, "+  HETI + " INTEGER" + ")" )
            db?.execSQL(createszemelyek)
           // db?.execSQL("insert into" + TBL_SZEMELYEK  +"(" + NEV +"," + ADOAZONOSITO +","+ FONOK +","+ MUNKAREND +","+ BELEPES +","+ EMAIL+","+ JELSZO +","+ SZABADSAG +","+ HETI +") values('Dotzi',234545632,'Kallai Imre',1, '2022.1.2','dotzi@gmail.com', 'abc',30,35)")
            db?.execSQL("insert into " + TBL_SZEMELYEK + " (" + NEV +"," + ADOAZONOSITO +","+ FONOK +","+ MUNKAREND +","+ BELEPES +","+ EMAIL+","+ JELSZO +","+ SZABADSAG +","+ HETI +") values('Judylein',234891632,'Kallai Imre',1, '2022.2.4','judy@gmail.com', '123',24,40)")
        db?.execSQL("insert into szemelyek(nev, adoazonosito,fonok, munkarend, belepes, email, jelszo, eves_szabadsag, heti_munkaido) values('Dotzi',234545632,'Kallai Imre',1, '2022.1.2','dotzi@gmail.com', 'abc',30,35)")

        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
          /*  db!!.execSQL("DROP TABLE IF EXISTS $TBL_SZEMELYEK")
            onCreate(db)*/
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
                        fonok = cursor.getString(cursor.getColumnIndexOrThrow(FONOK)),
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
                    fonok = cursor.getString(cursor.getColumnIndexOrThrow(FONOK)),
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
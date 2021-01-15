package com.example.kotlinsqlitelogin.sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kotlinsqlitelogin.model.User


class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")
    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    fun checkUser(email: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_ID
        )
        val db = this.writableDatabase
        val selection = COLUMN_USER_EMAIL + " = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return if (cursorCount > 0) {
            true
        } else false
    }

    fun checkUser(email: String, password: String): Boolean {
        val columns = arrayOf(
            COLUMN_USER_ID
        )
        val db = this.writableDatabase
        val selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?"
        val selectionArgs = arrayOf(email, password)
        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        return if (cursorCount > 0) {
            true
        } else false
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserManager.db"
        private const val TABLE_USER = "user"
        private const val COLUMN_USER_ID = "user_id"
        private const val COLUMN_USER_NAME = "user_name"
        private const val COLUMN_USER_EMAIL = "user_email"
        private const val COLUMN_USER_PASSWORD = "user_password"
    }
}
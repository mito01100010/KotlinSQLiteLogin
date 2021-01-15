package com.example.kotlinsqlitelogin.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinsqlitelogin.R


class UsersActivity : AppCompatActivity() {
    private var textViewName: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        textViewName = findViewById<View>(R.id.userText) as TextView
        val nameFromIntent = intent.getStringExtra("EMAIL")
        textViewName!!.text = "Welcome $nameFromIntent"
    }
}
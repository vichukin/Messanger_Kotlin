package com.example.messanger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun Login(view: View)
    {
        val login = findViewById<EditText>(R.id.login).text;
        val password = findViewById<EditText>(R.id.password).text;
        Log.i("MainActivity_Login","login: ${login} password: ${password}")
        finish()
    }
    fun registration(view: View)
    {
        val intent = Intent(this,RegistationActivity::class.java);
        startActivity(intent)
        finish()
    }
}
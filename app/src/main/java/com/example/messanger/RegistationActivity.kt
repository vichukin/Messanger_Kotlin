package com.example.messanger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText

class RegistationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)
    }
    fun registartion(view: View)
    {
        val log = findViewById<EditText>(R.id.login).text;
        val pas = findViewById<EditText>(R.id.password).text;

        val confPas = findViewById<EditText>(R.id.confPassword).text;
        if(pas.toString().equals(confPas.toString()))
        {
            Log.i("MainActivity_Registration","Register complete! login - ${log} password - ${pas}")
            finish();
        }
        else
            Log.e("MainActivity_Registration","Passwords doesn't match! pas - ${pas} conf pas - ${confPas}")

    }
    fun login(view:View)
    {
        val intent = Intent(this, LoginActivity::class.java)
        finish();
        startActivity(intent);
    }
}
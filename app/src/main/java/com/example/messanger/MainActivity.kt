package com.example.messanger

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Typeface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.messanger.Services.TestService

class MainActivity : AppCompatActivity() {
    var sPref: SharedPreferences? = null
    val Login = "login"
    val Password = "password"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        clearPrefs();
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            if(LoginFunctions.isLogin(this))
            {
                Log.i("Main","current user ${LoginFunctions.currentUser.id} ${LoginFunctions.currentUser.login}")
                openChats()
            }
            else
                openLoginActivity(null);
        }, 3000)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }
    fun openLoginActivity(view: View?)
    {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent);
        finish()
    }
    fun openChats()
    {
        startService(Intent(this, TestService::class.java));
        val intent = Intent(this,FragmentsActivity::class.java)
        startActivity(intent);
        finish()
    }
    fun clearPrefs() {
        sPref = getSharedPreferences("MyPreffs", MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sPref?.edit()
        editor?.clear()
        editor?.apply()
        Toast.makeText(this, "Preferences cleared", Toast.LENGTH_SHORT).show()
    }




}
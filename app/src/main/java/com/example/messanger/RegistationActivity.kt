package com.example.messanger

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.messanger.Models.MessangerDBHelper
import com.example.messanger.Models.User

class RegistationActivity : AppCompatActivity() {
    val dbHelper: MessangerDBHelper = MessangerDBHelper(this)
    val Login = "login"
    val Password = "password"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registation)
    }
    fun registartion(view: View)
    {
        val log = findViewById<EditText>(R.id.login).text.toString();
        val pas = findViewById<EditText>(R.id.password).text.toString();

        val confPas = findViewById<EditText>(R.id.confPassword).text.toString();
        if(User.checkIfExists(dbHelper,log))
        {
            Log.e("MainActivity_Registration","Login already exists")
            return
        }
        else if(!pas.equals(confPas))
        {
            Log.e("MainActivity_Registration","Passwords doesn't match! pas - ${pas} conf pas - ${confPas}")
            return
        }
        var user = User(log,pas);
        User.insertUser(dbHelper,user);
//        LoginFunctions.currentUser = user;
        saveTextPrefs(log,pas);
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent);
        finish()


    }
    fun saveTextPrefs(login: String, pass:String) {
        var sPref = getSharedPreferences("MyPreffs",MODE_PRIVATE)

        val ed: SharedPreferences.Editor? = sPref?.edit();
        ed?.putString(Login, login)
        ed?.putString(Password, pass)
        ed?.commit()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }
    fun login(view:View)
    {
        val intent = Intent(this, LoginActivity::class.java)
        finish();
        startActivity(intent);
    }
}
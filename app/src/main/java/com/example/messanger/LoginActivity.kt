package com.example.messanger

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.messanger.Models.MessangerDBHelper
import com.example.messanger.Models.User
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    var sPref: SharedPreferences? = null
    val Login = "login"
    val Password = "password"
    val dbHelper: MessangerDBHelper = MessangerDBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun Login(view: View)
    {
        val login = findViewById<EditText>(R.id.login).text.toString();
        val password = findViewById<EditText>(R.id.password).text.toString();

        var user = User.getUserByLogin(dbHelper,login);
        if(user!=null&& user.password.equals(password))
        {
//            LoginFunctions.currentUser = user;
            saveTextPrefs(login,password);
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent);
            finish()
        }
        else
        {
            Log.e("MainActivity_Login","Wrong login or password")
        }

    }
    fun saveTextPrefs(login: String, pass:String) {
        sPref = getSharedPreferences("MyPreffs",MODE_PRIVATE)

        val ed: SharedPreferences.Editor? = sPref?.edit();
        ed?.putString(Login, login)
        ed?.putString(Password, pass)
        ed?.commit()
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show()
    }
    fun registration(view: View)
    {
        val intent = Intent(this,RegistationActivity::class.java);
        startActivity(intent)
        finish()
    }
}
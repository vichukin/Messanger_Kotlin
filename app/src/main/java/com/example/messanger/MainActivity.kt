package com.example.messanger

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            if(LoginFunctions.isLogin())
                openChats()
            else
                openLoginActivity(null);
        }, 3000)

    }
    fun openLoginActivity(view: View?)
    {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent);
        finish()
    }
    fun openChats()
    {
        val intent = Intent(this,ChatsActivity::class.java)
        startActivity(intent);
        finish()
    }

}
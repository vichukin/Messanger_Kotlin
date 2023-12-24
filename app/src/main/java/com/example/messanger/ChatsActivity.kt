package com.example.messanger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.messanger.Constants.PARAM_TASK
import com.example.messanger.Constants.PARAM_TEXT_MSG
import com.example.messanger.Constants.PARAM_USERS_LIST
import com.example.messanger.Constants.PARAM_USER_NICK
import com.example.messanger.Constants.TASK_MSH
import com.example.messanger.Constants.TASK_USERS
import com.example.messanger.Models.Message
import com.example.messanger.Models.MessageAdapter
import com.example.messanger.Services.MessageBroadcastReceiver
import java.lang.Exception

class ChatsActivity : AppCompatActivity() {
    val LOG_TAG = "ChatActivity"
    var messages: ArrayList<Message>  = arrayListOf();
    var messagesList: ListView? = null
    private fun SetFont(view: TextView) {
        val myCustomFontBold = Typeface.createFromAsset(assets, "font/fontawesome_webfont.ttf")
        view.typeface = myCustomFontBold
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)
        initList()
        messagesList = findViewById<ListView>(R.id.messageList)
        messagesList?.setAdapter(FragmentsActivity.messageAdapter)
        val send_Btn = findViewById<Button>(R.id.send_btn)
        SetFont(send_Btn)
//        br = MessageBroadcastReceiver()
//        val intFilt = IntentFilter(Constants.BROADCAST_ACTION)
        // register BroadcastReceiver
//        try{
//            registerReceiver(br, intFilt)
//        }
//        catch (ex: Exception)
//        {
//            Log.e(LOG_TAG,ex.message.toString());
//        }



    }
    fun initList()
    {
        val message1 = Message(1, 2,  "Привет, как дела?")
        val message2 = Message(2,  3, "Привет! Всё отлично, спасибо!")
        val message3 = Message(3, 1, "Здравствуй, что нового?")

        messages.add(message1)
        messages.add(message2)
        messages.add(message3)
    }

    override fun onDestroy() {
        super.onDestroy()
        messages = arrayListOf()
        messagesList=null;
    }
    override fun onStop() {
        super.onStop()
        messages = arrayListOf()
        messagesList=null;


    }

}
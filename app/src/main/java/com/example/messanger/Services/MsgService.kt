package com.example.messanger.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.messanger.ChatFragment

class MsgService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    companion object {
        var chatFragment : ChatFragment? = null
    }
}
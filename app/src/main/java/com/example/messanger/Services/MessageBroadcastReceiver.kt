package com.example.messanger.Services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.messanger.Constants.PARAM_TASK
import com.example.messanger.Constants.PARAM_TEXT_MSG
import com.example.messanger.Constants.PARAM_USERS_LIST
import com.example.messanger.Constants.PARAM_USER_NICK
import com.example.messanger.Constants.TASK_MSH
import com.example.messanger.Constants.TASK_USERS

class MessageBroadcastReceiver: BroadcastReceiver() {

    val LOG_TAG = "MessageBroadcastReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        val task = intent.getIntExtra(PARAM_TASK, 0)
        if (task == TASK_MSH) {
            val msg = intent.getStringExtra(PARAM_TEXT_MSG)
            val user = intent.getStringExtra(PARAM_USER_NICK)
            Log.i(LOG_TAG, "$user - $msg")
            // showNotification(msg, user)
        } else if (task == TASK_USERS) {
            val usersList = intent.getStringExtra(PARAM_USERS_LIST)
            Log.i(LOG_TAG, usersList!!)
        }
    }
}
package com.example.messanger

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.messanger.Models.Message
import com.example.messanger.Models.MessageAdapter
import com.example.messanger.Services.TestService

class ChatFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {

            var textEdit = this.view?.findViewById<TextView>(R.id.messageContent)
            var text = textEdit?.text.toString()
            textEdit?.text = ""
            val message = Message(LoginFunctions.currentUser.id,text);
            messages.add(message);
            Message.insertMessage(FragmentsActivity.dbHelper,message);
            messagesList?.setAdapter(FragmentsActivity.messageAdapter)
    }
    companion object {
        var messages: ArrayList<Message>  = arrayListOf();
        var send_btn: Button? = null;
    }
    val  LOG_TAG = "ChatFragment"
    var myView: View? = null

    var messagesList: ListView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myView = inflater.inflate(R.layout.fragment_chat, container, false);

        if(messages.count()==0)
        {
            messages = Message.getMessages(FragmentsActivity.dbHelper)
        }

        messagesList = myView?.findViewById<ListView>(R.id.messageList)
        if(FragmentsActivity.messageAdapter==null)
            FragmentsActivity.messageAdapter= MessageAdapter(activity,R.layout.list_item,messages)
        messagesList?.setAdapter(FragmentsActivity.messageAdapter)
//        messagesList?.stackFromEnd = true;
        send_btn = myView?.findViewById<Button>(R.id.send_btn)
        send_btn?.setOnClickListener(this)
        SetFont(send_btn!!)
        return myView
    }
    private fun SetFont(view: Button) {
        val myCustomFontBold = Typeface.createFromAsset(requireContext().assets, "font/fontawesome_webfont.ttf")
        view.typeface = myCustomFontBold
    }
    fun sendGetRequest(msg:String,nick: String)
    {
//        (activity as FragmentsActivity).showNotification(nick,msg)
        Log.i(LOG_TAG,"Send get request: ${nick} - ${msg}");
    }

    override fun onStart() {
        super.onStart()
        TestService.chatFragment=this;

    }

    override fun onStop() {
        super.onStop()
        TestService.chatFragment=null;

    }
    fun initList()
    {
        val message1 = Message(1, 2,  "Привет, как дела?")
        val message2 = Message(2,  3,  "Привет! Всё отлично, спасибо!")
        val message3 = Message(3, 1, "Здравствуй, что нового?")

        messages.add(message1)
        messages.add(message2)
        messages.add(message3)
    }

}
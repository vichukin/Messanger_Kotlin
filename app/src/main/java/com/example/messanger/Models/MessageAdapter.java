package com.example.messanger.Models;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.messanger.R;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {
    public static boolean isExist=false;

    private LayoutInflater inflater;
    private int layout;
    private List<Message> messages;

    public MessageAdapter(Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
        this.messages = messages;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        MessageAdapter.isExist=true;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        TextView fromView = view.findViewById(R.id.from);
        TextView contentView = view.findViewById(R.id.content);
        LinearLayout layout1 = view.findViewById(R.id.messageListItem);

        Message message = messages.get(position);
        if(message.getUserId()==3)
            layout1.setGravity(5);
        fromView.setText(message.getUserId().toString());
        contentView.setText(message.getContent());

        return view;
    }
}
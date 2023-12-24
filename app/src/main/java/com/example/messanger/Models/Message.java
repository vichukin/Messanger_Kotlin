package com.example.messanger.Models;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Message {
    private Long id;
    private Long userId;
    private String content;
    private Timestamp sentAt;
    public Message(){}
    public Message(Long from_Id, String content) {
        this.userId = from_Id;
        this.content = content;
        this.sentAt = new Timestamp(new Date().getTime());
    }
    public Message(Long id, Long from_Id, String content) {
        this.id = id;
        this.userId = from_Id;
        this.content = content;
        this.sentAt = new Timestamp(new Date().getTime());
    }
    public Message(Long id, Long from_Id, String content,Timestamp sentAt) {
        this.id = id;
        this.userId = from_Id;
        this.content = content;
        this.sentAt = sentAt;
    }
    // Статический метод для получения всех сообщений
    public static ArrayList<Message> getMessages(MessangerDBHelper dbHelper) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] allColumns = {
                MessangerDBHelper.COLUMN_MESSAGE_ID,
                MessangerDBHelper.COLUMN_MESSAGE_USER_ID,
                MessangerDBHelper.COLUMN_MESSAGE_CONTENT,
                MessangerDBHelper.COLUMN_MESSAGE_SENT_AT
        };

        ArrayList<Message> messageList = new ArrayList<>();

        try {
            Cursor cursor = database.query(MessangerDBHelper.TABLE_MESSAGES, allColumns, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_MESSAGE_ID));
                    long userId = cursor.getLong(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_MESSAGE_USER_ID));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_MESSAGE_CONTENT));
                    Timestamp sentAt = Timestamp.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_MESSAGE_SENT_AT)));

                    messageList.add(new Message(id, userId, content));
                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (SQLiteException e) {
            // Обработка ошибок SQLite
            e.printStackTrace();
        }

        return messageList;
    }

    // Статический метод для вставки сообщения в базу данных
    public static long insertMessage(MessangerDBHelper dbHelper, Message message) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MessangerDBHelper.COLUMN_MESSAGE_USER_ID, message.getUserId());
        values.put(MessangerDBHelper.COLUMN_MESSAGE_CONTENT, message.getContent());
        values.put(MessangerDBHelper.COLUMN_MESSAGE_SENT_AT, message.getSentAt().toString());
        return database.insert(MessangerDBHelper.TABLE_MESSAGES, null, values);
    }

    public Long getId() {
        return id;
    }

//    public Long getTo_Id() {
//        return to_Id;
//    }

    public Long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public void setTo_Id(Long to_Id) {
//        this.to_Id = to_Id;
//    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}

package com.example.messanger.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MessangerDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "messenger.db";
    public static final int DATABASE_VERSION = 1;

    // Таблица сообщений
    public static final String TABLE_MESSAGES = "messages";
    public static final String COLUMN_MESSAGE_ID = "id";
    public static final String COLUMN_MESSAGE_USER_ID = "user_id";
    public static final String COLUMN_MESSAGE_CONTENT = "content";
    public static final String COLUMN_MESSAGE_SENT_AT = "sent_at";

    // Таблица пользователей
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_LOGIN = "login";
    public static final String COLUMN_USER_PASSWORD = "password";

    // SQL-запросы для создания таблиц
    private static final String CREATE_MESSAGES_TABLE =
            "CREATE TABLE " + TABLE_MESSAGES + "(" +
                    COLUMN_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MESSAGE_CONTENT + " TEXT, " +
                    COLUMN_MESSAGE_SENT_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    COLUMN_MESSAGE_USER_ID + " INTEGER, " +  // Обновленное имя столбца для связи с таблицей users
                    "FOREIGN KEY(" + COLUMN_MESSAGE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USER_ID + "));";

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + "(" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_LOGIN + " TEXT UNIQUE, " +
                    COLUMN_USER_PASSWORD + " TEXT);";

    public MessangerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицы при первом создании базы данных
        db.execSQL(CREATE_MESSAGES_TABLE);
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

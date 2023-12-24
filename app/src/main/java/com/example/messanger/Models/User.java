package com.example.messanger.Models;

import static android.content.Context.MODE_PRIVATE;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String login;
    private String Password;
    public User(String login, String password) {
        this.login = login;
        Password = password;
    }
    public User(Long id,String login, String password) {
        this.id=id;
        this.login = login;
        Password = password;
    }
    // Статический метод для получения всех пользователей
    public static List<User> getUsers(MessangerDBHelper dbHelper) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] allColumns = {MessangerDBHelper.COLUMN_USER_ID, MessangerDBHelper.COLUMN_USER_LOGIN, MessangerDBHelper.COLUMN_USER_PASSWORD};

        List<User> userList = new ArrayList<>();

        Cursor cursor = database.query(MessangerDBHelper.TABLE_USERS, allColumns, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int buf = cursor.getColumnIndex(MessangerDBHelper.COLUMN_USER_ID);
                Long id = cursor.getLong(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_USER_ID));
                String login = cursor.getString(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_USER_LOGIN));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_USER_PASSWORD));

                userList.add(new User(id, login, password));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userList;
    }
    public static long insertUser(MessangerDBHelper dbHelper, User user) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MessangerDBHelper.COLUMN_USER_LOGIN, user.getLogin());
        values.put(MessangerDBHelper.COLUMN_USER_PASSWORD, user.getPassword());
        return database.insert(MessangerDBHelper.TABLE_USERS, null, values);
    }

    // Статический метод для проверки существования пользователя по логину
    public static boolean checkIfExists(MessangerDBHelper dbHelper, String login) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + MessangerDBHelper.TABLE_USERS +
                " WHERE " + MessangerDBHelper.COLUMN_USER_LOGIN + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{login});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Статический метод для получения пользователя по логину
    public static User getUserByLogin(MessangerDBHelper dbHelper, String login) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String query = "SELECT * FROM " + MessangerDBHelper.TABLE_USERS +
                " WHERE " + MessangerDBHelper.COLUMN_USER_LOGIN + " = ?";
        Cursor cursor = database.rawQuery(query, new String[]{login});

        User user = null;
        if (cursor.moveToFirst()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_USER_ID));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(MessangerDBHelper.COLUMN_USER_PASSWORD));

            user = new User(id, login, password);
        }

        cursor.close();
        return user;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.example.messanger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.messanger.Models.MessangerDBHelper;
import com.example.messanger.Models.User;

public class LoginFunctions {
    public static boolean isLogin(Context context)
    {
        SharedPreferences sPref = context.getSharedPreferences("MyPreffs",Context.MODE_PRIVATE);
        String login = sPref.getString("login", "");
        String pass = sPref.getString("password", "");
        if(login!=""&&pass!="")
        {
            MessangerDBHelper dbHelper = new MessangerDBHelper(context);
            User user = User.getUserByLogin(dbHelper,login);
            LoginFunctions.currentUser = user;
            return true;
        }

        return false;
    }
    public static User currentUser = null;
}

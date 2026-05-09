package com.example.loginapp;

import android.content.Context;
import android.content.SharedPreferences;

public class ApiClient {
    // Replace with your PC's IP address (from 'ipconfig')
    public static final String BASE_URL = "http://192.168.1.59/loginApp/";
    
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    public ApiClient(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createSession(String id, String username, String email) {
        editor.putString(KEY_ID, id);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL, email);
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public UserModel getUserDetails() {
        return new UserModel(
            pref.getString(KEY_ID, null),
            pref.getString(KEY_USERNAME, null),
            pref.getString(KEY_EMAIL, null)
        );
    }

    public void logout() {
        editor.clear();
        editor.apply();
    }
}

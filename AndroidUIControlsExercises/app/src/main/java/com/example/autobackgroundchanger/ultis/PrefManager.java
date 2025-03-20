package com.example.autobackgroundchanger.ultis;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // Luu trang thia dang nhap
    public void setLoggedIn(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.apply();
    }

    // Kiem tra nguoi dung dang nhap hay chua
    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    // Dang xuat nguoi dung
    public void logout() {
        editor.clear();
        editor.apply();
    }
}

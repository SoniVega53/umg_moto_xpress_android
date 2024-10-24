package com.example.umg_moto_xpress_android.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.umg_moto_xpress_android.models.data.UserDecodeData;
import com.google.gson.Gson;
import com.securepreferences.SecurePreferences;

public class SharedPreferencesTool {
    private static SecurePreferences sp;
    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sharedPreferences;
    }

    public static SecurePreferences getSecureInstance(Context context) {
        if (sp == null) {
            sp = new SecurePreferences(context);
        }
        return sp;
    }

    public static void writeSecureBoolean(Context context, String key, Boolean value) {
        sp = getSecureInstance(context);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean readSecureBoolean(Context context, String key, Boolean defaultValue) {
        sp = getSecureInstance(context);
        return sp.getBoolean(key, defaultValue);
    }

    public static void writeSecureString(Context context, String key, String value) {
        sp = getSecureInstance(context);
        sp.edit().putString(key, value).apply();
    }

    public static String readSecureString(Context context, String key, String defaultValue) {
        sp = getSecureInstance(context);
        return sp.getString(key, defaultValue);
    }

    public static void cleanKey(Context context, String key) {
        sp = getSecureInstance(context);
        sp.edit().remove(key).apply();
    }


    public static void writeSecureUser(Context context, String key, UserDecodeData value) {
        sp = getSecureInstance(context);
        sp.edit().putString(key, new Gson().toJson(value)).apply();
    }

    public static UserDecodeData readSecureUser(Context context, String key, String defaultValue) {
        sp = getSecureInstance(context);
        return new Gson().fromJson(sp.getString(key, defaultValue),UserDecodeData.class);
    }
}

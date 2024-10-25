package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;

import com.example.umg_moto_xpress_android.models.data.UserDecodeData;
import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;

public class BaseRepository {

    protected UserDecodeData getUserDecodeData(Context context){
        return SharedPreferencesTool.readSecureUser(context, StringTool.LOGIN_USER,"");
    }
}

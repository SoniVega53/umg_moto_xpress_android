package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;

import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;

import okhttp3.Interceptor;
import okhttp3.Request;

public class InterceptorToken {

    public static Interceptor interceptor(Context context){
        Interceptor interceptor = chain -> {
            String token = SharedPreferencesTool.readSecureString(context, StringTool.LOGIN_SESSION,"");

            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + token) // Agregar el token

                    // Puedes agregar más encabezados si es necesario
                    .header("Accept", "application/json");

            Request newRequest = builder.build();

            return chain.proceed(newRequest);
        };

        return interceptor;
    }


}

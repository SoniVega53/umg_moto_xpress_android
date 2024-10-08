package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainRepositories {
    private static Retrofit retrofit;

    public static <S> S createService(Class<S> serviceClass, Context context) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.122:9090/api/proyecto/noauth/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(serviceClass);
    }
}

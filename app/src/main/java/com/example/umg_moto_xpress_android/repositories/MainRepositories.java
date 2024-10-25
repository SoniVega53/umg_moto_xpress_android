package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import com.example.umg_moto_xpress_android.tools.SharedPreferencesTool;
import com.example.umg_moto_xpress_android.tools.StringTool;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainRepositories {
    private static Retrofit retrofit;
    private static Retrofit retrofitToken;
    private static String URL_API_AUTH = "http://192.168.1.122:9090/api/proyecto/noauth/";
    private static String URL_API = "http://192.168.1.122:9090/api/proyecto/user/";
    public static String URL_API_IP = "";

    public static <S> S createService(Class<S> serviceClass, Context context) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_API_IP.isEmpty() ? UrlGateWay(context).concat("noauth/") : URL_API_IP.concat("noauth/"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }

    public static <S> S createServiceToken(Class<S> serviceClass, Context context) {
        if(retrofitToken == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(InterceptorToken.interceptor(context))
                    .build();

            retrofitToken = new Retrofit.Builder()
                    .baseUrl(URL_API_IP.isEmpty() ? UrlGateWay(context).concat("user/") : URL_API_IP.concat("user/"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitToken.create(serviceClass);
    }

    private static String UrlGateWay(Context context){
        String gatewayIP = getGatewayIP(context);
        if (gatewayIP != null) {
            return "http://".concat(gatewayIP).concat(":9090/api/proyecto/");
        }
        return URL_API;
    }

    public static String getGatewayIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            return Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
        }
        return null;
    }


}

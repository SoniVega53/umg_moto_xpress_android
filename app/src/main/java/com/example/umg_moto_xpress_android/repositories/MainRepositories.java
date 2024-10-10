package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainRepositories {
    private static Retrofit retrofit;
    private static String URL_API = "http://192.168.1.122:9090/api/proyecto/noauth/";

    public static <S> S createService(Class<S> serviceClass, Context context) {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlGateWay(context))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }

    private static String UrlGateWay(Context context){
        String gatewayIP = getGatewayIP(context);
        if (gatewayIP != null) {
            return "http://".concat(gatewayIP).concat(":9090/api/proyecto/noauth/");
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

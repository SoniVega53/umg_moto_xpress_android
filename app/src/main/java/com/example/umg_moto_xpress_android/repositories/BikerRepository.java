package com.example.umg_moto_xpress_android.repositories;

import android.content.Context;

import com.example.umg_moto_xpress_android.network.BikerApi;

public class BikerRepository {

    private static BikerRepository bikerRepository;
    private BikerApi bikerApi;
    private Context context;

    public BikerRepository(Context context) {
        this.context = context;
        bikerApi = MainRepositories.createServiceToken(BikerApi.class,context);
    }

    public static BikerRepository getInstance(Context context){
        if (bikerRepository == null){
            bikerRepository = new BikerRepository(context);
        }
        return bikerRepository;
    }
}

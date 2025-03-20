package com.example.autobackgroundchanger.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if(retrofit ==null){
            retrofit = new Retrofit.Builder()
                    //Duong dan API
                    .baseUrl("http://192.168.88.107:8080/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

package com.example.autobackgroundchanger.network;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("sendcode")
    Call<ResponseBody> sendOtp(@Query("email") String email);

    @POST("register")
    Call<ResponseBody> registerUser(@Body Map<String, String> body);
}

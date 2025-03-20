package com.example.autobackgroundchanger.network;

import com.example.autobackgroundchanger.models.UserModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("sendcode")
    Call<String> sendOtp(@Query("email") String email);

    @POST("login")
    Call<ResponseBody> login(@Body UserModel user);

    @POST("register")
    Call<ResponseBody> registerUser(@Body UserModel userModel, @Query("otp") String otp);

}

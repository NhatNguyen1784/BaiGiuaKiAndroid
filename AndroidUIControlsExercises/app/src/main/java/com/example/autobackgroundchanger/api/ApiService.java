package com.example.autobackgroundchanger.api;

import com.example.autobackgroundchanger.model.Category;
import com.example.autobackgroundchanger.model.LoginRequest;
import com.example.autobackgroundchanger.model.Product;
import com.example.autobackgroundchanger.model.UserModel;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @POST("login")
    Call<String> login(@Body LoginRequest request);

    @GET("category")
    Call<List<Category>> getCategoryAll();

    @GET("product")
    Call<List<Product>> getLastProducts();

    @POST("sendcode")
    Call<String> sendOtp(@Query("email") String email);

    @POST("register")
    Call<ResponseBody> registerUser(@Body UserModel userModel, @Query("otp") String otp);
}


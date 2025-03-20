package com.example.autobackgroundchanger.api;

import com.example.autobackgroundchanger.model.LoginResponse;
import com.example.autobackgroundchanger.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}

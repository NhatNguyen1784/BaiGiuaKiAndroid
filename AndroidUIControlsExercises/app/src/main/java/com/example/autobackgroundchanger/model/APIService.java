package com.example.autobackgroundchanger.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("categry/getCategory")
    Call<List<Category>> getCategoryAll();
}

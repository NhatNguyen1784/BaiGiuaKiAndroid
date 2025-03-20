package com.example.autobackgroundchanger;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobackgroundchanger.adapter.CategoryAdapter;
import com.example.autobackgroundchanger.model.APIService;
import com.example.autobackgroundchanger.model.Category;
import com.example.autobackgroundchanger.model.RetrofitClient;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCate;
    //Khai bao adapter
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();
    }

    private void AnhXa(){
        rvCate = (RecyclerView) findViewById(R.id.rvCategories);

    }

    private void GetCategory(){
        // Goi interface trong API Service
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoryAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    categoryList = response.body();
                    // Khoi tao adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rvCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rvCate.setLayoutManager(layoutManager);
                    rvCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                }
                else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Log", t.getMessage());
            }
        });
    }
}
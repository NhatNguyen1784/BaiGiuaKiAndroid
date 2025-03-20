package com.example.autobackgroundchanger.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autobackgroundchanger.R;
import com.example.autobackgroundchanger.adapter.CategoryAdapter;

import com.example.autobackgroundchanger.adapter.ProductAdapter;
import com.example.autobackgroundchanger.api.ApiService;
import com.example.autobackgroundchanger.api.RetrofitClient;
import com.example.autobackgroundchanger.model.Category;

import com.example.autobackgroundchanger.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCate;
    RecyclerView rvLastProducts;
    //Khai bao adapter
    CategoryAdapter categoryAdapter;
    ProductAdapter productAdapter;
    ApiService apiService;
    List<Category> categoryList;
    List<Product> productList;

    TextView info;
    ImageView logoutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();
		GetLastProducts();
        Getthongtin();
        logoutLayout = findViewById(R.id.imageView5112);
        logout();
    }
    private void logout ()
    {
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa trạng thái đăng nhập
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Xóa toàn bộ dữ liệu UserData
                editor.apply();

                // Chuyển đến LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng MainActivity để không quay lại bằng Back
            }
        });
    }
    private void Getthongtin() {
        info = findViewById(R.id.infoUser); // Ánh xạ TextView từ layout

        // Lấy dữ liệu từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "Chưa có thông tin đăng nhập");

        // Hiển thị username lên TextView
        info.setText("Hi! " + username);
    }


    private void AnhXa(){
        rvCate = findViewById(R.id.rvCategories);
        rvLastProducts = findViewById(R.id.rvLastProducts);
    }

    private void GetCategory(){
        // Goi interface trong API Service
        
        apiService = RetrofitClient.getRetrofit().create(ApiService.class);
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

    private void GetLastProducts(){
        // Goi interface trong API Service
        apiService = RetrofitClient.getRetrofit().create(ApiService.class);
        apiService.getLastProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    productList = response.body();
                    productAdapter = new ProductAdapter(MainActivity.this, productList);
                    rvLastProducts.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    rvLastProducts.setLayoutManager(layoutManager);
                    rvLastProducts.setAdapter(productAdapter);
                    productAdapter.notifyDataSetChanged();
                } else {
                    int statusCode = response.code();
                    Log.d("Logg Lỗi product", statusCode + "");
                    // Xử lý lỗi
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("Logssss", t.getMessage());
            }
        });
    }
}
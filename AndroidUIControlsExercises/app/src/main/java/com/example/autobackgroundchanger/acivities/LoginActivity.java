package com.example.autobackgroundchanger.acivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autobackgroundchanger.R;
import com.example.autobackgroundchanger.models.UserModel;
import com.example.autobackgroundchanger.network.ApiService;
import com.example.autobackgroundchanger.network.RetrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private ImageButton loginButton;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_layout);
        // Ánh xạ UI
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        loginButton = findViewById(R.id.imageButton);

        // Khởi tạo Retrofit API service
        Retrofit retrofit = RetrofitClient.getRetrofit();
        apiService = retrofit.create(ApiService.class);

        // Xử lý sự kiện khi nhấn nút đăng nhập
        loginButton.setOnClickListener(view -> loginUser());
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel user = new UserModel(email, password);
        Call<ResponseBody> call = apiService.login(user);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful()) {
                        String responseBody = response.body().string(); // Đọc dữ liệu dạng text
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang HomeActivity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("LOGIN_ERROR", "Lỗi khi đọc phản hồi: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("LOGIN_ERROR", "Lỗi kết nối: " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Lỗi kết nối đến server!", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

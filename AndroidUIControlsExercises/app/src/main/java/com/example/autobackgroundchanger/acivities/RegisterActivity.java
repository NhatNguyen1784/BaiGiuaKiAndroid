package com.example.autobackgroundchanger.acivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autobackgroundchanger.R;
import com.example.autobackgroundchanger.models.UserModel;
import com.example.autobackgroundchanger.network.ApiService;
import com.example.autobackgroundchanger.network.RetrofitClient;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword, edtOtp;
    private Button btnSendOtp, btnRegister;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_layout);
        edtEmail = findViewById(R.id.editTextTextEmailAddress);
        edtPassword = findViewById(R.id.editTextTextPassword);
        edtOtp = findViewById(R.id.editTextOtp);
        btnSendOtp = findViewById(R.id.btnSendOtp);
        btnRegister = findViewById(R.id.btnRegisterUser);
        
        Retrofit retrofit = RetrofitClient.getRetrofit();
        apiService = retrofit.create(ApiService.class);

        btnSendOtp.setOnClickListener(view -> sendOtp());
        btnRegister.setOnClickListener(view -> registerUser());
    }

    private void sendOtp() {
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService.sendOtp(email).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this, "OTP sent: " + response.body(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Failed to send OTP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registerUser() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String otp = edtOtp.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty() || otp.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        UserModel user = new UserModel(email, password);

        apiService.registerUser(user, otp).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseText = response.body().string();
                        Log.d("API_RESPONSE", "Response: " + responseText);
                        Toast.makeText(RegisterActivity.this, responseText, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Đóng RegisterActivity
                    } else {
                        // Lấy thông báo lỗi từ API
                        String errorResponse = response.errorBody() != null ? response.errorBody().string() : "Unknown error";
                        Log.e("API_ERROR", "Error: " + errorResponse);
                        Toast.makeText(RegisterActivity.this, errorResponse, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Error parsing response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("API_FAILURE", "Error: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}

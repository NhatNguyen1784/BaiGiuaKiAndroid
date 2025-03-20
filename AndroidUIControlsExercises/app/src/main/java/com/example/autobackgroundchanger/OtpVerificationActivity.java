package com.example.autobackgroundchanger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autobackgroundchanger.network.ApiService;
import com.example.autobackgroundchanger.network.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerificationActivity extends AppCompatActivity {
    private EditText etOtp, etPassword;
    private Button btnVerifyOtp;
    private ApiService apiService;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        etOtp = findViewById(R.id.etOtp);
        etPassword = findViewById(R.id.etPassword);
        btnVerifyOtp = findViewById(R.id.btnVerifyOtp);

        apiService = RetrofitClient.getApiService();
        email = getIntent().getStringExtra("email");

        btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOtpAndRegister();
            }
        });
    }

    private void verifyOtpAndRegister() {
        String otp = etOtp.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (otp.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter OTP and Password", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("otp", otp);
        requestBody.put("password", password);

        apiService.registerUser(requestBody).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(OtpVerificationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OtpVerificationActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(OtpVerificationActivity.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(OtpVerificationActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

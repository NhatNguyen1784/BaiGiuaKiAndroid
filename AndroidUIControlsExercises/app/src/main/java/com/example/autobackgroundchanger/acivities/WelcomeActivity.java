package com.example.autobackgroundchanger.acivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.autobackgroundchanger.R;
import com.example.autobackgroundchanger.ultis.PrefManager;

public class WelcomeActivity extends AppCompatActivity {
    private PrefManager pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        pref = new PrefManager(this);
        Button btnNext = findViewById(R.id.startBtn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pref.isLoggedIn()) {
                    // Nếu đã đăng nhập, chuyển đến màn hình chính
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                } else {
                    // Nếu chưa đăng nhập, chuyển đến màn hình đăng nhập
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                }
                finish();
            }
        });
    }
}

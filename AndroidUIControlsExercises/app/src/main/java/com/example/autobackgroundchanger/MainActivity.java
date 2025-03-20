package com.example.autobackgroundchanger;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mainLayout; // Định nghĩa layout chính của activity
    // Mảng chứa danh sách hình nền
    private int[] backgrounds = {
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4,
            R.drawable.bg5,
            R.drawable.bg6
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ConstraintLayout bg = (ConstraintLayout)
                findViewById(R.id.main);

        // Chọn ngẫu nhiên một hình nền từ mảng backgrounds
        int randomIndex = new Random().nextInt(backgrounds.length);

        // Đặt hình nền cho layout chính
        bg.setBackgroundResource(backgrounds[randomIndex]);

        Switch sw = (Switch) findViewById(R.id.switch_toggle);
        // Đổi màu nút trượt (thumb) và thanh trượt (track)
//        sw.setThumbTintList(ColorStateList.valueOf(Color.RED)); // Nút trượt màu đỏ
//        sw.setTrackTintList(ColorStateList.valueOf(Color.RED)); // Thanh trượt màu xám
        // Phóng to 1.5 lần
        sw.setScaleX(1.5f);
        sw.setScaleY(1.5f);

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            int temp = -1; // Đặt -1 để tránh trùng với chỉ mục hợp lệ

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { // Khi switch bật
                    int randomIndex;
                    do {
                        randomIndex = new Random().nextInt(backgrounds.length);
                    } while (randomIndex == temp); // Lặp đến khi chọn số khác

                    temp = randomIndex; // Cập nhật temp
                    bg.setBackgroundResource(backgrounds[randomIndex]);
                }
            }
        });


    }
}
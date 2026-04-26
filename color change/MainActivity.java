package com.example.change_background;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main);

        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        Button btn3 = findViewById(R.id.button3);

        btn1.setOnClickListener(v -> changeBackground(R.drawable.bg1));
        btn2.setOnClickListener(v -> changeBackground(R.drawable.bg2));
        btn3.setOnClickListener(v -> changeBackground(R.drawable.bg3));
    }

    private void changeBackground(int drawableId) {
        layout.setBackgroundResource(drawableId);
    }
}
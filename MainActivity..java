package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {

            Intent i = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(i);

        });
    }
}
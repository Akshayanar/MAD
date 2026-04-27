package com.example.progressdemo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnPhoto;
    ProgressBar circularBar, linearBar;
    TextView tvPercent;

    ActivityResultLauncher<String> galleryLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnPhoto = findViewById(R.id.btnPhoto);
        circularBar = findViewById(R.id.circularBar);
        linearBar = findViewById(R.id.linearBar);
        tvPercent = findViewById(R.id.tvPercent);

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        imageView.setImageURI(uri);
                        startUploading();
                    }
                });

        btnPhoto.setOnClickListener(v -> galleryLauncher.launch("image/*"));
    }

    private void startUploading() {

        circularBar.setVisibility(View.VISIBLE);
        linearBar.setVisibility(View.VISIBLE);
        tvPercent.setVisibility(View.VISIBLE);

        Handler handler = new Handler();

        for (int i = 1; i <= 100; i++) {

            int progress = i;

            handler.postDelayed(() -> {

                linearBar.setProgress(progress);
                tvPercent.setText(progress + "%");

                if (progress == 100) {
                    circularBar.setVisibility(View.GONE);
                    tvPercent.setText("Upload Complete ✅");
                }

            }, i * 40);
        }
    }
}
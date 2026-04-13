package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btnRead, btnProgress, btnUpload;
    TextView textView, ratingText, filePath;
    RatingBar ratingBar;
    ProgressBar progressBar;

    int progressStatus = 0;
    private static final int FILE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize
        editText = findViewById(R.id.editText);
        btnRead = findViewById(R.id.btnRead);
        textView = findViewById(R.id.textView);
        ratingBar = findViewById(R.id.ratingBar);
        ratingText = findViewById(R.id.ratingText);
        progressBar = findViewById(R.id.progressBar);
        btnProgress = findViewById(R.id.btnProgress);
        btnUpload = findViewById(R.id.btnUpload);
        filePath = findViewById(R.id.filePath);

        // Read Text
        btnRead.setOnClickListener(v -> {
            String data = editText.getText().toString();
            textView.setText(data);
        });

        // RatingBar
        ratingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            ratingText.setText("Rating: " + rating);
        });

        // Progress Bar
        btnProgress.setOnClickListener(v -> {
            progressStatus = 0;

            new Thread(() -> {
                while (progressStatus < 100) {
                    progressStatus += 10;

                    runOnUiThread(() ->
                            progressBar.setProgress(progressStatus)
                    );

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        // File Upload
        btnUpload.setOnClickListener(v -> openFileChooser());
    }

    private void openFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            filePath.setText("Selected: " + uri.toString());
        }
    }
}
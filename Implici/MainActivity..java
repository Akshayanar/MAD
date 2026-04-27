package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBrowser = findViewById(R.id.btnBrowser);

        btnBrowser.setOnClickListener(v -> {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.google.com"));
            startActivity(i);

        });
    }
}
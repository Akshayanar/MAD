package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.equals("akshayanar06042@gmail.com") &&
                    pass.equals("1234")) {

                Toast.makeText(MainActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show();

                Intent i = getPackageManager()
                        .getLaunchIntentForPackage("com.example.profile_demo");

                if (i != null) {
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Second App Not Installed",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(MainActivity.this,
                        "Wrong Email or Password",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}
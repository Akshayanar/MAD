package com.example.intent;

import android.content.Intent;
import android.net.Uri; // Import the Uri class
import android.os.Bundle;
import android.view.View; // Import the View class
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    EditText et;
    Button b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.Text);
        // Correct the ID from "Serch" to "Search"
        b5 = (Button) findViewById(R.id.Search);

        b5.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg0)
            {
                String link = et.getText().toString();

                Intent i = new Intent(
                        android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://" + link));

                startActivity(i);
            }
        });
    }
}

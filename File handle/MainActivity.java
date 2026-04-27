package com.example.filehandlingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText etFileName, etData;
    Button btnSave, btnRead, btnDelete;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFileName = findViewById(R.id.etFileName);
        etData = findViewById(R.id.etData);
        btnSave = findViewById(R.id.btnSave);
        btnRead = findViewById(R.id.btnRead);
        btnDelete = findViewById(R.id.btnDelete);
        tvOutput = findViewById(R.id.tvOutput);

        // Save File
        btnSave.setOnClickListener(v -> {
            String file = etFileName.getText().toString();
            String data = etData.getText().toString();

            try {
                FileOutputStream fos = openFileOutput(file, MODE_PRIVATE);
                fos.write(data.getBytes());
                fos.close();

                Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Read File
        btnRead.setOnClickListener(v -> {
            String file = etFileName.getText().toString();

            try {
                FileInputStream fis = openFileInput(file);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);

                String line;
                StringBuilder data = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    data.append(line).append("\n");
                }

                tvOutput.setText(data.toString());

                fis.close();

            } catch (Exception e) {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete File
        btnDelete.setOnClickListener(v -> {
            String file = etFileName.getText().toString();

            boolean deleted = deleteFile(file);

            if (deleted) {
                Toast.makeText(this, "File Deleted", Toast.LENGTH_SHORT).show();
                tvOutput.setText("");
            } else {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
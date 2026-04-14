package com.example.inputcontrol;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String selectedDate = "Not Set";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitchMaterial switchPriority = findViewById(R.id.switchPriority);
        Spinner spinnerRole = findViewById(R.id.spinnerRole);
        SeekBar seekBar = findViewById(R.id.seekBarProgress);
        TextView tvSeekLabel = findViewById(R.id.tvSeekLabel);
        Button btnDate = findViewById(R.id.btnDate);
        Button btnSave = findViewById(R.id.btnSave);
        TextView tvSummary = findViewById(R.id.tvSummary);

        // Spinner
        String[] options = {"College Work", "Self Work"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, options) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(Color.parseColor("#6A5ACD"));
                return tv;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        // Switch
        switchPriority.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                switchPriority.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#FF8A80")));
            } else {
                switchPriority.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#81D4FA")));
            }
        });

        // SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekLabel.setText("Progress: " + progress + "%");
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Date Picker
        btnDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) -> {
                selectedDate = d + "/" + (m + 1) + "/" + y;
                btnDate.setText("Date: " + selectedDate);
            }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        // Save Button
        btnSave.setOnClickListener(v -> {
            boolean isHigh = switchPriority.isChecked();
            String category = spinnerRole.getSelectedItem().toString();
            int progress = seekBar.getProgress();

            String result = "Task Summary\n\n" +
                    "Priority: " + (isHigh ? "URGENT 🔥" : "Normal") + "\n" +
                    "Category: " + category + "\n" +
                    "Date: " + selectedDate + "\n" +
                    "Progress: " + progress + "%";

            tvSummary.setText(result);

            Toast.makeText(this, "Saved ✅", Toast.LENGTH_SHORT).show();
        });
    }
}
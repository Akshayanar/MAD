package com.example.contact;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> contactPickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                        Uri uri = result.getData().getData();
                        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

                        if (cursor != null && cursor.moveToFirst()) {

                            int nameIndex = cursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

                            int numberIndex = cursor.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER);

                            String name = cursor.getString(nameIndex);
                            String number = cursor.getString(numberIndex);

                            cursor.close();

                            Intent i = new Intent(MainActivity.this, ContactDetailActivity.class);
                            i.putExtra("name", name);
                            i.putExtra("phone", number);
                            startActivity(i);
                        }
                    }
                });

        findViewById(R.id.btnContact).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            contactPickerLauncher.launch(intent);
        });
    }
}
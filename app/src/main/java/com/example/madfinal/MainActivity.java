package com.example.madfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button donor_button;
    Button hospital_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        donor_button = findViewById(R.id.donor_button);
        hospital_button = findViewById(R.id.hospital_button);
        donor_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DonorRegisterActivity.class);
            startActivity(intent);
        });
        hospital_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}
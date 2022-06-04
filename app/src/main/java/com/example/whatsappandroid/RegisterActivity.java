package com.example.whatsappandroid;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnBack = findViewById(R.id.BackButton);
        btnBack.setOnClickListener(arg0 -> finish());
    }
}
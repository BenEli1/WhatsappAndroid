package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        String username = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        String password = editTextPassword.getText().toString();

        Button btnBack = findViewById(R.id.BackButton);
        btnBack.setOnClickListener(arg0 -> finish());
        Button btnLogin = findViewById(R.id.RegisterButton);
        btnLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, ChatListActivity.class);
            i.putExtra("username", username);
            i.putExtra("password",password);
            startActivity(i);
        });
    }
}
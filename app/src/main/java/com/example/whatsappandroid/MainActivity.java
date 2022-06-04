package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        String username = editTextUsername.getText().toString();
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        String password = editTextPassword.getText().toString();
        Button btnRegister = findViewById(R.id.RegisterButton);
        btnRegister.setOnClickListener(v -> {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
        });
        Button btnLogin = findViewById(R.id.LoginButton);
        btnLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, ChatListActivity.class);
            i.putExtra("username", username);
            i.putExtra("password",password);
            startActivity(i);
        });

    }
}
package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandroid.R;

public class SettingsActivity extends AppCompatActivity {
private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Intent activityIntent = getIntent();
        Button btnBack = findViewById(R.id.back_button_setting);
        btnBack.setOnClickListener(arg0 -> finish());
        Button btnSave = findViewById(R.id.save_setting_changes);
        username = activityIntent.getStringExtra("Username");

        btnBack.setOnClickListener( v->
        {
//            EditText new_server=findViewById(R.id.editTextServerSettings);
            Intent i = new Intent(this, ChatListActivity.class);
            i.putExtra("Username", username);
//            i.putExtra("Server", new_server.getText().toString());
            startActivity(i);
        });
    }
}
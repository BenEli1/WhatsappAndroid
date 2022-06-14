package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandroid.CreatedClasses.BaseUrl;
import com.example.whatsappandroid.R;

public class SettingsActivity extends AppCompatActivity {
    private EditText new_server;
    private TextView changed_server;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Intent activityIntent = getIntent();
        Button btnBack = findViewById(R.id.back_button_setting);
        btnBack.setOnClickListener(arg0 -> finish());
        Button btnSave = findViewById(R.id.save_setting_changes);
        btnSave.setOnClickListener( v->
        {
            new_server=findViewById(R.id.editTextServerSettings);
            BaseUrl.setBaseUrl(new_server.getText().toString());
            changed_server=findViewById(R.id.changed_server);
            changed_server.setText("Changed to : ".concat(BaseUrl.baseUrl));
        });
    }
}
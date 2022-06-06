package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Intent activityIntent = getIntent();
        String userName;
        if (activityIntent != null) {
            userName = activityIntent.getStringExtra("Username");
        } else {
            userName = "";
        }
        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();
        Button backContactButton = findViewById(R.id.backContactButton);
        backContactButton.setOnClickListener(arg0 -> finish());

        Button addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(view->{
            EditText etUsername= findViewById(R.id.editTextUsername2);
            EditText etNickname= findViewById(R.id.editTextNickName2);
            EditText etServer= findViewById(R.id.editTextServer);

            Contact contact = new Contact(etUsername.getText().toString(),
                    0,"",etNickname.getText().toString(),etServer.getText().toString(),"",userName);
            contactDao.insert(contact);
            finish();
        });
    }
}
package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.AppUserDB;
import com.example.whatsappandroid.Classes.User;
import com.example.whatsappandroid.R;
import com.example.whatsappandroid.api.UserAPI;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AppUserDB db;
    private com.example.whatsappandroid.Dao.UserDao UserDao;
    private List<User> users;
    private UserAPI userAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = Room.databaseBuilder(getApplicationContext(),
                AppUserDB.class, "UsersDB").allowMainThreadQueries().build();
        UserDao = db.userDao();
        users = new ArrayList<User>();
        userAPI = new UserAPI(users, UserDao);
        Button btnBack = findViewById(R.id.BackButton);
        btnBack.setOnClickListener(arg0 -> finish());
        Button btnLogin = findViewById(R.id.RegisterButton);
        btnLogin.setOnClickListener(v -> {

            EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
            String username = editTextUsername.getText().toString();

            EditText editTextNickName = (EditText) findViewById(R.id.editTextNickname);
            String nickName = editTextNickName.getText().toString();

            EditText editTextPasswordCheck = (EditText) findViewById(R.id.editTextPasswordCheck);
            String passwordCheck = editTextPasswordCheck.getText().toString();

            EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            String password = editTextPassword.getText().toString();

            // if everything is valid
            User newUser = new User(username, nickName, password, "");
            UserDao.insert(newUser);
            userAPI.post(newUser);

            Intent i = new Intent(this, ChatListActivity.class);
            i.putExtra("Username", username);
            i.putExtra("password",password);
            startActivity(i);
        });
    }
}
package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.api.ContactAPI;
import com.example.whatsappandroid.api.UserAPI;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppUserDB db;
    private UserDao UserDao;
    private List<User> users;
    private UserAPI userAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppUserDB.class, "UsersDB").allowMainThreadQueries().build();
        UserDao = db.userDao();
        users = new ArrayList<User>();
        userAPI = new UserAPI(users, UserDao);
        Button btnRegister = findViewById(R.id.RegisterButton);
        btnRegister.setOnClickListener(v -> {
            Intent i = new Intent(this, RegisterActivity.class);
            startActivity(i);
        });
        Button btnLogin = findViewById(R.id.LoginButton);
        btnLogin.setOnClickListener(v -> {
            userAPI.get();
            users.addAll(UserDao.index());
            Intent i = new Intent(this, ChatListActivity.class);
            EditText editTextUsername = findViewById(R.id.editTextUsernameLogin);
            String username = editTextUsername.getText().toString();
            EditText editTextPassword = findViewById(R.id.editTextPasswordLogin);
            String password = editTextPassword.getText().toString();
            //users.addAll(UserDao.index());
            for(User user : users){
                if(user.getUserUserName().equals(username)  && user.getPassword().equals(password)){
                    i.putExtra("Username", username);
                    startActivity(i);
                    break;
                }
            }
            //put error message(incorrect password or username)
        });

    }
}
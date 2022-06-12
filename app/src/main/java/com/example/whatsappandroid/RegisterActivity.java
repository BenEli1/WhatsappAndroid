package com.example.whatsappandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.api.UserAPI;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private AppUserDB db;
    private UserDao UserDao;
    private List<User> users;
    private UserAPI userAPI;
    private EditText editTextUsername;
    private EditText editTextNickName;
    private EditText editTextPasswordCheck;
    private EditText editTextPassword;

    private void cleanAllFeilds(){
        editTextUsername.setText("");
        editTextNickName.setText("");
        editTextPasswordCheck.setText("");
        editTextPassword.setText("");
    }

    private void errorValidation(String Title,String messageError)
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(RegisterActivity.this);
        builder.setMessage(messageError);
        builder.setTitle(Title);
        builder.setCancelable(false);
        builder
                .setNegativeButton(
                        "OK",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


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

            editTextUsername = (EditText) findViewById(R.id.editTextUsername);
            String username = editTextUsername.getText().toString();

            editTextNickName = (EditText) findViewById(R.id.editTextNickname);
            String nickName = editTextNickName.getText().toString();

            editTextPasswordCheck = (EditText) findViewById(R.id.editTextPasswordCheck);
            String passwordCheck = editTextPasswordCheck.getText().toString();

            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            String password = editTextPassword.getText().toString();

            // if everything is valid
            if(username.equals("")){
               cleanAllFeilds();
               errorValidation(getString(R.string.payAttenrion),getString(R.string.UsernameRequired));
                return;
            }
            if(nickName.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.NicknameRequired));
                return;
            }
            if(password.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.PasswordRequired));
                return;
            }
            if(passwordCheck.equals("")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.ConfirmPasswordReqires));
                return;
            }
            if(username.length() < 3){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.InvalidUsername));
                return;
            }

            if(!password.matches("^(?=.*[0-9])(?=.*[A-Z])[a-zA-Z0-9!@#$%^&*]{6,20}$")){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion), getString(R.string.PasswordInvalid));
                return;
            }

            if(!password.equals(passwordCheck)){
                cleanAllFeilds();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.PasswordsNotEqual));
                return;
            }

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
package com.example.whatsappandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.api.ContactAPI;
import com.example.whatsappandroid.api.InvitationAPI;

import java.util.ArrayList;
import java.util.List;

public class AddContactActivity extends AppCompatActivity {
    private AppDB db;
    private ContactDao contactDao;
    private EditText etUsername;
    private EditText etNickname;
    private EditText etServer;

    private void cleatAllFields(){
        etUsername.setText("");
        etNickname.setText("");
        etServer.setText("");
    }

    private void errorValidation(String Title,String messageError)
    {
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(AddContactActivity.this);
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
        setContentView(R.layout.activity_add_contact);

        Intent activityIntent = getIntent();
        String userName;
        if (activityIntent != null) {
            userName = activityIntent.getStringExtra("username");
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
            etUsername = findViewById(R.id.editTextUsername2);
            String usernameText = etUsername.getText().toString();
            etNickname = findViewById(R.id.editTextNickName2);
            String nickNameText = etNickname.getText().toString();
            etServer = findViewById(R.id.editTextServer);
            String serverText = etServer.getText().toString();

            if(usernameText.equals("")){
                cleatAllFields();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.UsernameRequired));
                return;
            }
            if(nickNameText.equals("")){
                cleatAllFields();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.NicknameRequired));
                return;
            }
            if(serverText.equals("")){
                cleatAllFields();
                errorValidation(getString(R.string.payAttenrion),getString(R.string.ServerRequired));
                return;
            }

            List<Contact> contacts = new ArrayList<Contact>(contactDao.index(userName));
            for(Contact contact : contacts){
                if(contact.getId().equals(usernameText)){
                    cleatAllFields();
                    errorValidation(getString(R.string.payAttenrion),getString(R.string.ContactAlreadyExist));
                    return;
                }
            }

            Contact contact = new Contact(usernameText,
                    0,"",nickNameText,serverText,"", userName);
            ContactAPI contactAPI = new ContactAPI(null, contactDao, userName);
            contactAPI.post(contact);
            contactDao.insert(contact);

            List<Invitation> invitations = new ArrayList<Invitation>();

            //here the server is the new contact's server
            InvitationAPI invitationAPI = new InvitationAPI(invitations, serverText);

            //here the server is my server!!
            Invitation invitation = new Invitation(userName,usernameText, MyApplication.context.getString(R.string.BaseUrl));
            invitationAPI.post(invitation);
            finish();
        });
    }
}
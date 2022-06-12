package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.api.ContactAPI;
import com.example.whatsappandroid.api.MessageAPI;
import com.example.whatsappandroid.api.UserAPI;

import java.util.ArrayList;
import java.util.List;

public class SingleChatActivity extends AppCompatActivity {

    ImageView profilePictureView;
    TextView userNameView;
    private String UserName;
    private String ContactUserName;
    private String ContactNickName;
    private AppMessageDB db;
    private MessageDao messageDao;
    private List<Message> messages;
    private MessageAPI messageAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        messages = new ArrayList<Message>();
        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            ContactUserName = activityIntent.getStringExtra("ContactUserName");
            ContactNickName = activityIntent.getStringExtra("ContactNickName");
            UserName = activityIntent.getStringExtra("Username");
            int profilePicture = activityIntent.getIntExtra("profilePicture", R.drawable.blue);
            profilePictureView = findViewById(R.id.user_image_profile_image);
            userNameView = findViewById(R.id.user_text_user_name);
            profilePictureView.setImageResource(profilePicture);
            userNameView.setText("username: " + UserName + " is talking with: " + ContactNickName);
        }

        db = Room.databaseBuilder(getApplicationContext(),
                AppMessageDB.class, "MessageDB").allowMainThreadQueries().build();
        messageDao = db.messageDao();
        //contacts = contactDao.index();
        messageAPI = new MessageAPI(messages, messageDao, UserName, ContactUserName);


    }
}
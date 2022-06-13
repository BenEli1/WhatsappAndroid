package com.example.whatsappandroid;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsappandroid.api.ContactAPI;
import com.example.whatsappandroid.api.MessageAPI;
import com.example.whatsappandroid.api.UserAPI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class SingleChatActivity extends AppCompatActivity {

    ImageView profilePictureView;
    TextView userNameView;
    private String UserName;
    private String ContactUserName;
    private String ContactNickName;
    private AppMessageDB dbMessage;
    private AppDB dbContact;
    private MessageDao messageDao;
    private ContactDao contactDao;
    private List<Message> messages;
    private MessageAPI messageAPI;
    private CustomMsgAdapter adapter;
    private ListView listView;
    private RecyclerView mMessageRecycler;
    private CustomMsgAdapter mMessageAdapter;
    @RequiresApi(api = Build.VERSION_CODES.O)
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
            //profilePictureView.setImageResource(profilePicture);

        }
        TextView viewContact = findViewById(R.id.current_user);
        viewContact.setText(this.ContactNickName);

        dbMessage = Room.databaseBuilder(getApplicationContext(),
                AppMessageDB.class, "MessageDB").allowMainThreadQueries().build();
        messageDao = dbMessage.messageDao();

        dbContact = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = dbContact.contactDao();

        //contacts = contactDao.index();
        messageAPI = new MessageAPI(messages, messageDao, UserName, ContactUserName);

        //messageAPI.get();
        messages.clear();
        messages.addAll(messageDao.index(UserName, ContactUserName));
        adapter = new CustomMsgAdapter(getApplicationContext(), messages);
        mMessageRecycler = (RecyclerView) findViewById(R.id.msg_view);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessageRecycler.setAdapter(mMessageAdapter);
        mMessageRecycler.setAdapter(adapter);

        ImageView sendMessage = findViewById(R.id.send_button);
        sendMessage.setOnClickListener(view -> {
            EditText text = findViewById(R.id.Edit_Text_Msg_Send);
            String messageText = text.getText().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Message newMessage = new Message(this.ContactUserName, this.UserName, messageText, now.toString(), "true");
            messageDao.insert(newMessage);
            messageAPI.post(newMessage);
            Contact contact = contactDao.get(ContactUserName, UserName);
            contact.setLastdate(now.toString());
            contact.setLast(messageText);
            contactDao.update(contact);
            messages.clear();
            messages.addAll(messageDao.index(UserName, ContactUserName));
            mMessageRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });


    }
}
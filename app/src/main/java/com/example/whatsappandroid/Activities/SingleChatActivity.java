package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.whatsappandroid.AppDB;
import com.example.whatsappandroid.AppMessageDB;
import com.example.whatsappandroid.AppUserImageDB;
import com.example.whatsappandroid.CreatedClasses.BaseUrl;
import com.example.whatsappandroid.CreatedClasses.Contact;
import com.example.whatsappandroid.Dao.ContactDao;
import com.example.whatsappandroid.Adapter.CustomMsgAdapter;
import com.example.whatsappandroid.CreatedClasses.Message;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.R;
import com.example.whatsappandroid.CreatedClasses.UserImage;
import com.example.whatsappandroid.Dao.UserImageDao;
import com.example.whatsappandroid.api.MessageAPI;
import com.example.whatsappandroid.api.TransferAPI;
import com.example.whatsappandroid.transfer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private String contactServer;
    private AppUserImageDB userImageDB;
    private UserImageDao userImageDao;
    private List<UserImage> userImages;

    private Bitmap convertBase64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);

        messages = new ArrayList<Message>();
        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            contactServer = activityIntent.getStringExtra("server");
            ContactUserName = activityIntent.getStringExtra("ContactUserName");
            ContactNickName = activityIntent.getStringExtra("ContactNickName");
            UserName = activityIntent.getStringExtra("Username");
            int profilePicture = activityIntent.getIntExtra("profilePicture", R.drawable.blue);
            profilePictureView = findViewById(R.id.user_image_profile_image);
            //profilePictureView.setImageResource(profilePicture);

        }

        userImageDB = Room.databaseBuilder(getApplicationContext(),
                AppUserImageDB.class, "UserImagesDB").allowMainThreadQueries().build();
        userImageDao = userImageDB.userImageDao();
        userImages = new ArrayList<>();
        userImages.addAll(userImageDao.index());

        ImageView image = findViewById(R.id.current_img);

        for(UserImage userImage : userImages){
            if(userImage.getUsername().equals(ContactUserName)){
                image.setImageBitmap(convertBase64ToBitmap(userImage.getImage()));
            }
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

            //the message
            EditText text = findViewById(R.id.Edit_Text_Msg_Send);
            String messageText = text.getText().toString();

            //check that the message is not empty
            if(messageText.equals("")){
                return;
            }

            //clear the message
            text.setText("");

            //generate date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            //create the new message
            Message newMessage = new Message(this.ContactUserName, this.UserName, messageText, now.toString(), "true");

            //send the new message
            messageDao.insert(newMessage);
            messageAPI.post(newMessage);

            //change the contact
            Contact contact = contactDao.get(ContactUserName, UserName);
            contact.setLastdate(now.toString());
            contact.setLast(messageText);
            contactDao.update(contact);

            //display the new message
            messages.clear();
            messages.addAll(messageDao.index(UserName, ContactUserName));
            mMessageRecycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //send the transfer
            List<transfer> transfers = new ArrayList<transfer>();
            TransferAPI transferAPI = new TransferAPI(transfers, contactServer);
            transfer transfer = new transfer(UserName, ContactUserName, messageText);
            transferAPI.post(transfer);
        });

    }
}
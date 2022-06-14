package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.AppDB;
import com.example.whatsappandroid.AppUserImageDB;
import com.example.whatsappandroid.CreatedClasses.Contact;
import com.example.whatsappandroid.Dao.ContactDao;
import com.example.whatsappandroid.Adapter.CustomListAdapter;
import com.example.whatsappandroid.R;
import com.example.whatsappandroid.CreatedClasses.UserImage;
import com.example.whatsappandroid.Dao.UserImageDao;
import com.example.whatsappandroid.api.ContactAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {
    final private int[] profilePictures = {
            R.drawable.blue, R.drawable.gold, R.drawable.green,
            R.drawable.red, R.drawable.lightblue, R.drawable.custom_button
    };
    private ListView listView;
    private CustomListAdapter adapter;
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactAPI contactAPI;
    private String userName;
    private String server;
    private AppUserImageDB userImageDB;
    private UserImageDao userImageDao;
    private List<UserImage> userImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        contacts = new ArrayList<Contact>();
        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            userName = activityIntent.getStringExtra("Username");
            server=activityIntent.getStringExtra("Server");
        } else {
            userName = "";
            server="";
        }
        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();
        //contacts = contactDao.index();
        contactAPI = new ContactAPI(contacts, contactDao, userName);
        userImageDB = Room.databaseBuilder(getApplicationContext(),
                AppUserImageDB.class, "UserImagesDB").allowMainThreadQueries().build();
        userImageDao = userImageDB.userImageDao();
        userImages = new ArrayList<>();
        userImages.addAll(userImageDao.index());
        contactAPI.get();
        listView = findViewById(R.id.list_view);
        adapter = new CustomListAdapter(getApplicationContext(), contacts, userImages);
        FloatingActionButton fab = findViewById(R.id.btnAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddContactActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), SingleChatActivity.class);

                intent.putExtra("Username", userName);
                intent.putExtra("ContactUserName", contacts.get(i).getId());
                intent.putExtra("ContactNickName", contacts.get(i).getName());
                intent.putExtra("profilePicture", profilePictures[i]);
                intent.putExtra("lastMassage", contacts.get(i).getLast());
                intent.putExtra("time", contacts.get(i).getLastdate());
                intent.putExtra("server", contacts.get(i).getServer());

                startActivity(intent);
            }
        });
        FloatingActionButton fab_setting = findViewById(R.id.btnSetting);
        fab_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
        listView.setClickable(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contactAPI.get();
        contacts.clear();
        contacts.addAll(contactDao.index(userName));
        adapter.notifyDataSetChanged();
    }
}

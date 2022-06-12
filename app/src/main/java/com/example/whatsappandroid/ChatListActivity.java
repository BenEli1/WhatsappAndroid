package com.example.whatsappandroid;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.whatsappandroid.api.ContactAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChatListActivity extends AppCompatActivity {
    final private int[] profilePictures = {
            R.drawable.blue, R.drawable.gold, R.drawable.green,
            R.drawable.red, R.drawable.lightblue, R.drawable.custom_button
    };
//    final private String[] userNames = {
//            "Blue User", "Golden User", "Green User", "Red User", "Lightblue User", "Gray User"
//    };
//
//    final private String[] lastMassages = {
//            "Hi, how are you?", "24K Magic", "I'm GREEN!", "Red is my name", "wasap :)", "Yo!"
//    };
//
//    final private String[] times = {
//            "12:00", "00:30", "3:23", "8:59", "14:52", "12:23"
//    };


    private ListView listView;
    private CustomListAdapter adapter;
    private AppDB db;
    private ContactDao contactDao;
    private List<Contact> contacts;
    private ContactAPI contactAPI;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        contacts = new ArrayList<Contact>();
        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            userName = activityIntent.getStringExtra("Username");
        } else {
            userName = "";
        }
        db = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = db.contactDao();
        //contacts = contactDao.index();
        contactAPI = new ContactAPI(contacts, contactDao, userName);
        contactAPI.get();
        listView = findViewById(R.id.list_view);
        adapter = new CustomListAdapter(getApplicationContext(), contacts);
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
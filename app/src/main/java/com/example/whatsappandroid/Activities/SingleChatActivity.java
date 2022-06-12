package com.example.whatsappandroid.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsappandroid.AppDB;
import com.example.whatsappandroid.Classes.Message;
import com.example.whatsappandroid.Adapters.CustomMsgAdapter;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SingleChatActivity extends AppCompatActivity {


    private ListView listView;
    private CustomMsgAdapter adapter;
    private AppDB db;
    private MessageDao msgDao;
    private List<Message> messages;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_chat);
        messages = new ArrayList<Message>();

        Intent activityIntent = getIntent();

        if (activityIntent != null) {
            userName = activityIntent.getStringExtra("Username");
        } else {
            userName = "";
        }
        listView = findViewById(R.id.list_view);
        ImageView btnSend = findViewById(R.id.send_button);
        EditText msgToSend = findViewById(R.id.Edit_Text_Msg_Send);

        listView.setAdapter(adapter);
        listView.setClickable(true);
    }
}
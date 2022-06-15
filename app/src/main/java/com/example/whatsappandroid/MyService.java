package com.example.whatsappandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.room.Room;

import com.example.whatsappandroid.CreatedClasses.BaseUrl;
import com.example.whatsappandroid.CreatedClasses.Contact;
import com.example.whatsappandroid.CreatedClasses.Invitation;
import com.example.whatsappandroid.CreatedClasses.Message;
import com.example.whatsappandroid.Dao.ContactDao;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.api.ContactAPI;
import com.example.whatsappandroid.api.MessageAPI;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyService extends FirebaseMessagingService {

    private AppDB contactDb;
    private AppMessageDB messageDb;
    private ContactDao contactDao;
    private MessageDao messageDao;


    public MyService() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        contactDb = Room.databaseBuilder(getApplicationContext(),
                AppDB.class, "ContactsDB").allowMainThreadQueries().build();
        contactDao = contactDb.contactDao();
        messageDb = Room.databaseBuilder(getApplicationContext(),
                AppMessageDB.class, "MessageDB").allowMainThreadQueries().build();
        messageDao = messageDb.messageDao();



        if(remoteMessage.getNotification() != null){
            createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"1")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1,builder.build());

            if(remoteMessage.getNotification().getTitle().equals("Invitaion")){
                String s = remoteMessage.getNotification().getBody();
                String[] str = s.split("@",2);
                String contactName = str[0];
                String username = str[1];

                Contact contact = new Contact(contactName, 0, "", contactName, BaseUrl.baseUrl, "", username);
                contactDao.insert(contact);
            }else {

                String s = remoteMessage.getNotification().getTitle();
                String[] str = s.split("@");
                String contactName = str[0];
                String username = str[1];

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd HH:mm");
                LocalDateTime now = LocalDateTime.now();

                Message message = new Message(contactName, username, remoteMessage.getNotification().getBody(), now.toString(), "false");
                messageDao.insert(message);

                Contact contact = contactDao.get(contactName, username);
                contact.setLastdate(now.toString());
                contact.setLast(remoteMessage.getNotification().getBody());
                contactDao.update(contact);

                Contact contact2 = contactDao.get(username, contactName);
                contact2.setLastdate(now.toString());
                contact2.setLast(remoteMessage.getNotification().getBody());
                contactDao.update(contact2);
            }
        }
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            CharSequence name = "Channel 1";
            String description = "Main Channel";
            NotificationChannel channel= new NotificationChannel("1",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
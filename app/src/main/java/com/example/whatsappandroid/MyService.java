package com.example.whatsappandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {
    public MyService() {

    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if(message.getNotification()!=null){
            createNotificationChannel();
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"1")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(message.getNotification().getTitle())
                    .setContentText(message.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(1,builder.build());


        }
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel= new NotificationChannel("1","My channel",importance);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
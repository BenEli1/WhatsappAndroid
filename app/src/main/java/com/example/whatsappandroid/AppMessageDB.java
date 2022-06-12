package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsappandroid.Classes.Message;
import com.example.whatsappandroid.Classes.User;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.Dao.UserDao;

@Database(entities = {Message.class}, version = 1)
public abstract class AppMessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}

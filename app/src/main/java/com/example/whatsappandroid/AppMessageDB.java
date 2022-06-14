package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsappandroid.CreatedClasses.Message;
import com.example.whatsappandroid.Dao.MessageDao;

@Database(entities = {Message.class}, version = 1)
public abstract class AppMessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}

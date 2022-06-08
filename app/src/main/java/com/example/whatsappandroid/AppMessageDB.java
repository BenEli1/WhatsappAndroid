package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Message.class}, version = 1)
public abstract class AppMessageDB extends RoomDatabase {
    public abstract MessageDao messageDao();
}

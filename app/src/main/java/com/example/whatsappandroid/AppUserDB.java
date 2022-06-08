package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppUserDB extends RoomDatabase {
    public abstract UserDao userDao();
}

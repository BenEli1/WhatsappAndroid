package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsappandroid.Classes.User;
import com.example.whatsappandroid.Dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppUserDB extends RoomDatabase {
    public abstract UserDao userDao();
}

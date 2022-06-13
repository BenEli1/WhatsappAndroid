package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserImage.class}, version = 1)
public abstract class AppUserImageDB extends RoomDatabase {
    public abstract UserImageDao userImageDao();
}

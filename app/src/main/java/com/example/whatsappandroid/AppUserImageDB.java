package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsappandroid.CreatedClasses.UserImage;
import com.example.whatsappandroid.Dao.UserImageDao;

@Database(entities = {UserImage.class}, version = 1)
public abstract class AppUserImageDB extends RoomDatabase {
    public abstract UserImageDao userImageDao();
}

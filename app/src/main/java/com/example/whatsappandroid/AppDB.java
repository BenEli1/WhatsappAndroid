package com.example.whatsappandroid;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.whatsappandroid.Classes.Contact;
import com.example.whatsappandroid.Dao.ContactDao;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ContactDao contactDao();
}


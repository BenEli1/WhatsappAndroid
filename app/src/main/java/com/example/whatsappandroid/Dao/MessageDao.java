package com.example.whatsappandroid.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsappandroid.CreatedClasses.Message;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message WHERE UserName= :Username and contactName = :ContactName")
    List<Message> index(String Username, String ContactName);
    @Query("SELECT * FROM Message WHERE Id= :id")
    Message get(int id);
    @Insert
    void insert(Message... messages);
    @Update
    void update(Message... messages);
    @Delete
    void delete(Message... messages);
}

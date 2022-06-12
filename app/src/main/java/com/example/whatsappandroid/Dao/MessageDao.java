package com.example.whatsappandroid.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsappandroid.Classes.Message;
import com.example.whatsappandroid.Classes.User;

import java.util.List;

@Dao
public interface MessageDao {
    @Query("SELECT * FROM Message")
    List<Message> index();
    @Query("SELECT * FROM Message WHERE Id= :id")
    User get(int id);
    @Insert
    void insert(Message... messages);
    @Update
    void update(Message... messages);
    @Delete
    void delete(Message... messages);
}

package com.example.whatsappandroid.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatsappandroid.Classes.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> index();
    @Query("SELECT * FROM User WHERE UserName= :username")
    User get(String username);
    @Insert
    void insert(User... users);
    @Update
    void update(User... users);
    @Delete
    void delete(User... users);
}

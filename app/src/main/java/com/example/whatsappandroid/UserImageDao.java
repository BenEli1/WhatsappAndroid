package com.example.whatsappandroid;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserImageDao {
    @Query("SELECT * FROM UserImage")
    List<UserImage> index();
    @Query("SELECT * FROM UserImage WHERE UserName = :username")
    UserImage get(String username);
    @Insert
    void insert(UserImage... users);
    @Update
    void update(UserImage... users);
    @Delete
    void delete(UserImage... users);
}

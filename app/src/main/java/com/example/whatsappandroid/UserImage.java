package com.example.whatsappandroid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserImage {
    @PrimaryKey
    @NonNull
    private String Username;
    private String Image;

    UserImage(){

    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUsername() {
        return Username;
    }

    public String getImage() {
        return Image;
    }

    public UserImage(String username, String image) {
        Username = username;
        Image = image;
    }


}

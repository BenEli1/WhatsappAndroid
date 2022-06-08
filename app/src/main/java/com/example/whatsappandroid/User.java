package com.example.whatsappandroid;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey @NonNull
    private String UserName;
    private String NickName;
    private String Password;
    private String Image;

    User(String userName, String nickName, String password, String image) {
        UserName = userName;
        NickName = nickName;
        Password = password;
        Image = image;
    }

    User(){

    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setServer(String server) {
        Server = server;
    }

    public String getUserUserName() {
        return UserName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getNickName() {
        return NickName;
    }

    public String getPassword() {
        return Password;
    }

    public String getImage() {
        return Image;
    }

    public String getServer() {
        return Server;
    }

    private String Server;
}

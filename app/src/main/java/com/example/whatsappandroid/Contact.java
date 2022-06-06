package com.example.whatsappandroid;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate=true)
    private int id;
    private String userName;
    private int pictureId;
    private String lastMassage;
    private String Nickname;
    private String Server;

    public String getNickname() {
        return Nickname;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Contact() {
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public void setServer(String server) {
        Server = server;
    }

    public void setLastMassageSendingTime(String lastMassageSendingTime) {
        this.lastMassageSendingTime = lastMassageSendingTime;
    }

    public String getServer() {
        return Server;
    }

    public int getId() {
        return id;
    }

    private String lastMassageSendingTime;

    public void setId(int id) {
        this.id = id;
    }

    public Contact(String userName, int pictureId, String lastMassage, String lastMassageSendingTime,
                   String nickname,String server) {
        this.userName = userName;
        this.pictureId = pictureId;
        this.lastMassage = lastMassage;
        this.lastMassageSendingTime = lastMassageSendingTime;
        this.Nickname=nickname;
        this.Server=server;
    }

    public int getPictureId() {
        return pictureId;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public String getLastMassageSendingTime() {
        return lastMassageSendingTime;
    }

    public String getUserName() {
        return userName;
    }
}

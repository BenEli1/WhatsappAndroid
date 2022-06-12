package com.example.whatsappandroid.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
@Entity(primaryKeys = {"id", "username"})
public class Contact {
    @NonNull
    private String id;
    private String name;
    private int pictureId;
    private String last;
    private String server;
    @NonNull
    private String username;
    private String lastdate;

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getLastdate() {
        return lastdate;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Contact() {
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }


    public String getServer() {
        return server;
    }

    public Contact(String id, int pictureId, String last, String name, String server, String lastdate, String username) {
        this.id = id;
        this.pictureId = pictureId;
        this.last = last;
        this.name = name;
        this.server = server;
        this.lastdate = lastdate;
        this.username = username;
    }

    public int getPictureId() {
        return pictureId;
    }

    public String getLast() {
        return last;
    }


}

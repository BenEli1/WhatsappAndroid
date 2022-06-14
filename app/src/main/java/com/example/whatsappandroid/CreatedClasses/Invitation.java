package com.example.whatsappandroid.CreatedClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Invitation {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String from;

    public void setId(int id) {
        Id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setServer(String server) {
        this.server = server;
    }

    private String to;

    public int getId() {
        return Id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getServer() {
        return server;
    }

    private String server;

    public Invitation(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
}

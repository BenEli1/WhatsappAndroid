package com.example.whatsappandroid.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey @NonNull
    private int Id;
    private String ContactName;
    private String Contect;
    private String Created;
    private String Sent;

    public Message(int id, String contactName, String contect, String created, String sent) {
        Id = id;
        ContactName = contactName;
        Contect = contect;
        Created = created;
        Sent = sent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContect() {
        return Contect;
    }

    public void setContect(String contect) {
        Contect = contect;
    }

    public String getCreated() {
        return Created;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public String getSent() {
        return Sent;
    }

    public void setSent(String sent) {
        Sent = sent;
    }

    Message(){

    }

}

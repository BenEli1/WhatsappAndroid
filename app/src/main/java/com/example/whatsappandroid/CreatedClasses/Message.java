package com.example.whatsappandroid.CreatedClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String contactName;
    private String UserName;
    private String Contect;
    private String Created;
    private String Sent;

    public Message(){

    }

    public Message(String contect, String created, String sent) {
        Contect = contect;
        Created = created;
        Sent = sent;
    }

    public Message(String contactName, String userName, String contect, String created, String sent) {
        this.contactName = contactName;
        UserName = userName;
        Contect = contect;
        Created = created;
        Sent = sent;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setContect(String contect) {
        Contect = contect;
    }

    public void setCreated(String created) {
        Created = created;
    }

    public void setSent(String sent) {
        Sent = sent;
    }

    public int getId() {
        return Id;
    }

    public String getContactName() {
        return contactName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getContect() {
        return Contect;
    }

    public String getCreated() {
        return Created;
    }

    public String getSent() {
        return Sent;
    }
}





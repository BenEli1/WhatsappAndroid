package com.example.whatsappandroid;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class transfer {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String from;
    private String to;
    private String content;

    public void setId(int id) {
        Id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return Id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    public transfer(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }


}

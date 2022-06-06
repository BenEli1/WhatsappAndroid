package com.example.whatsappandroid.api;

import com.example.whatsappandroid.Contact;

import java.util.List;

public interface WebServiceAPI {
    @GET("contacts")
    Call<List<Contact>> getContacts(String Username);

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact,String Username);
}

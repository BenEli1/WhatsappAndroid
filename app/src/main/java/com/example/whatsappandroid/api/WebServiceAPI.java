package com.example.whatsappandroid.api;

import com.example.whatsappandroid.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {
    @GET("contacts")
    Call<List<Contact>> getContacts(String username);

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact, String Username);
}

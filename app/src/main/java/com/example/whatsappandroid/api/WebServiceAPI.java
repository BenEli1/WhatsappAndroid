package com.example.whatsappandroid.api;

import com.example.whatsappandroid.CreatedClasses.Contact;
import com.example.whatsappandroid.CreatedClasses.Invitation;
import com.example.whatsappandroid.CreatedClasses.Message;
import com.example.whatsappandroid.CreatedClasses.User;
import com.example.whatsappandroid.transfer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServiceAPI {

    @GET("api/Users")
    Call<List<User>> getUsers();

    @POST("api/Users")
    Call<Void> postUser(@Body User user);

    @GET("api/contacts")
    Call<List<Contact>> getContacts(@Query("username") String username);

    @POST("api/contacts")
    Call<Void> createContact(@Body Contact contact, @Query("username") String Username);

    @GET("api/contacts/{id}/messages")
    Call<List<Message>> getMessages(@Path("id") String contactName, @Query("username") String Username);

    @POST("api/contacts/{id}/messages")
    Call<Void> postMessage(@Path("id") String contactName, @Query("username") String Username,@Body Message message );

    @GET("api/transfer")
    Call<List<transfer>> getTransfers();

    @POST("api/transfer")
    Call<Void> postTransfer(@Body transfer transfer);

    @GET("api/invitations")
    Call<List<Invitation>> getInvitation();

    @POST("api/invitations")
    Call<Void> postInvitation(@Body Invitation invitation);
}

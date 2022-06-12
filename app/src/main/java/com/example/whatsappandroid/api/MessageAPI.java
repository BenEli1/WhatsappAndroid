package com.example.whatsappandroid.api;

import com.example.whatsappandroid.Classes.Contact;
import com.example.whatsappandroid.Classes.Message;
import com.example.whatsappandroid.Dao.ContactDao;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {
    private List<Contact> postListData;
    private MessageDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String Username;

    public MessageAPI(List<Contact> postListData, MessageDao dao,String username) {
        this.postListData = postListData;
        this.dao = dao;
        this.Username=username;

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Message>> call = webServiceAPI.getMessages(this.Username);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                postListData.clear();
                postListData.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }

    public void post(Contact contact) {
        Call call = webServiceAPI.createContact(contact,this.Username);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }
        });
    }
}

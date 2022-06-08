package com.example.whatsappandroid.api;

import com.example.whatsappandroid.Contact;
import com.example.whatsappandroid.ContactDao;
import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
    private List<Contact> postListData;
    private ContactDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String Username;

    public ContactAPI(List<Contact> postListData, ContactDao dao,String username) {
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
        Call<List<Contact>> call = webServiceAPI.getContacts(this.Username);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, retrofit2.Response<List<Contact>> response) {
                postListData.clear();
                postListData.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                postListData.clear();
                postListData.addAll(dao.index(Username));
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

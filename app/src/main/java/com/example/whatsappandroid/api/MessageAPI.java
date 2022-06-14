package com.example.whatsappandroid.api;

import com.example.whatsappandroid.CreatedClasses.BaseUrl;
import com.example.whatsappandroid.CreatedClasses.Message;
import com.example.whatsappandroid.Dao.MessageDao;
import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageAPI {

    private List<Message> postListData;
    private MessageDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String UserName;
    private String ContactName;

    public MessageAPI(List<Message> postListData, MessageDao dao, String userName, String contactName) {
        this.postListData = postListData;
        this.dao = dao;
        this.ContactName = contactName;
        this.UserName = userName;

        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Message>> call = webServiceAPI.getMessages(ContactName, UserName);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, retrofit2.Response<List<Message>> response) {
                postListData.clear();
                postListData.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                postListData.clear();
                postListData.addAll(dao.index(UserName, ContactName));
            }
        });
    }

    public void post(Message message) {
        Call call = webServiceAPI.postMessage(ContactName, UserName, message);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, retrofit2.Response<List<Message>> response) {
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }
        });
    }
}

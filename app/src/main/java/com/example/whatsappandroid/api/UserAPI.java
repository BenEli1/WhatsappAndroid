package com.example.whatsappandroid.api;

import com.example.whatsappandroid.Contact;
import com.example.whatsappandroid.ContactDao;
import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;
import com.example.whatsappandroid.User;
import com.example.whatsappandroid.UserDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {
    private List<User> postListData;
    private UserDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public UserAPI(List<User> postListData, UserDao dao) {
        this.postListData = postListData;
        this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<User>> call = webServiceAPI.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                postListData.clear();
                postListData.addAll(response.body());

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }

    public void post(User user) {
        Call call = webServiceAPI.postUser(user);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }
}

package com.example.whatsappandroid.api;

import androidx.lifecycle.MutableLiveData;

import com.example.whatsappandroid.Contact;
import com.example.whatsappandroid.ContactDao;
import com.example.whatsappandroid.R;
import com.google.android.gms.common.api.Response;

import java.util.List;

public class ContactAPI {
    private MutableLiveData<List<Contact>> postListData;
    private ContactDao dao;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;

    public ContactAPI(MutableLiveData<List<Contact>> postListData, ContactDao dao) {
        this.postListData = postListData;
        this.dao = dao;

        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Contact>> call = webServiceAPI.getContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {

                response.body();
            }

                @Override
                public void onFailure (Call < List < Contact >> call, Throwable t){
                }
            });
        }

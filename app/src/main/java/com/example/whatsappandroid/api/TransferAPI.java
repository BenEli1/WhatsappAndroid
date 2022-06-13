package com.example.whatsappandroid.api;

import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;
import com.example.whatsappandroid.transfer;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferAPI {
    private List<transfer> postListData;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String server;

    public TransferAPI(List<transfer> postListData, String Server) {
        this.postListData = postListData;
        this.server = Server;
        retrofit = new Retrofit.Builder()
                .baseUrl(this.server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<transfer>> call = webServiceAPI.getTransfers();
        call.enqueue(new Callback<List<transfer>>() {
            @Override
            public void onResponse(Call<List<transfer>> call, retrofit2.Response<List<transfer>> response) {
                postListData.clear();
                postListData.addAll(response.body());
            }
            @Override
            public void onFailure(Call<List<transfer>> call, Throwable t) {
            }
        });
    }

    public void post(transfer transfer) {
        Call call = webServiceAPI.postTransfer(transfer);
        call.enqueue(new Callback<List<transfer>>() {
            @Override
            public void onResponse(Call<List<transfer>> call, retrofit2.Response<List<transfer>> response) {
            }
            @Override
            public void onFailure(Call<List<transfer>> call, Throwable t) {
            }
        });
    }

}

package com.example.whatsappandroid.api;
import com.example.whatsappandroid.CreatedClasses.Invitation;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationAPI {
    private List<Invitation> postListData;
    private Retrofit retrofit;
    private WebServiceAPI webServiceAPI;
    private String server;

    public InvitationAPI(List<Invitation> postListData, String Server) {
        this.postListData = postListData;
        this.server = Server;
        retrofit = new Retrofit.Builder()
                .baseUrl(this.server)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void get() {
        Call<List<Invitation>> call = webServiceAPI.getInvitation();
        call.enqueue(new Callback<List<Invitation>>() {
            @Override
            public void onResponse(Call<List<Invitation>> call, retrofit2.Response<List<Invitation>> response) {
                postListData.clear();
                postListData.addAll(response.body());
            }
            @Override
            public void onFailure(Call<List<Invitation>> call, Throwable t) {
            }
        });
    }

    public void post(Invitation invitation) {
        Call call = webServiceAPI.postInvitation(invitation);
        call.enqueue(new Callback<List<Invitation>>() {
            @Override
            public void onResponse(Call<List<Invitation>> call, retrofit2.Response<List<Invitation>> response) {
            }
            @Override
            public void onFailure(Call<List<Invitation>> call, Throwable t) {
            }
        });
    }

}

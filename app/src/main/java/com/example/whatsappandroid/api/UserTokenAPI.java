package com.example.whatsappandroid.api;

import com.example.whatsappandroid.CreatedClasses.BaseUrl;
import com.example.whatsappandroid.CreatedClasses.User;
import com.example.whatsappandroid.CreatedClasses.UserToken;
import com.example.whatsappandroid.Dao.UserDao;
import com.example.whatsappandroid.MyApplication;
import com.example.whatsappandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserTokenAPI {
        //private List<UserToken> postListData;
        private Retrofit retrofit;
        private WebServiceAPI webServiceAPI;

        public UserTokenAPI() {
            //this.postListData = postListData;

            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            webServiceAPI = retrofit.create(WebServiceAPI.class);
        }

        /*public void get() {
            Call<List<User>> call = webServiceAPI.getUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                    postListData.clear();
                    postListData.addAll(response.body());
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    postListData.clear();
                    postListData.addAll(dao.index());
                }
            });
        }*/

        public void post(UserToken userToken) {
            Call call = webServiceAPI.postUserToken(userToken);
            call.enqueue(new Callback<List<UserToken>>() {
                @Override
                public void onResponse(Call<List<UserToken>> call, retrofit2.Response<List<UserToken>> response) {
                }

                @Override
                public void onFailure(Call<List<UserToken>> call, Throwable t) {
                }
            });
        }

}

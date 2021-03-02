package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel  extends AndroidViewModel {
    public NewsViewModel(@NonNull Application application) {

        super(application);
    }

    public void getNews (final GetCallBack callBack) {
        ApiClient.create().getNewsList().enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }


            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}

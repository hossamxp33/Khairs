package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.PartnersResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartnersViewModel extends AndroidViewModel {
    public PartnersViewModel(@NonNull Application application) {

        super(application);
    }

    public void getPartners(String type , final GetCallBack callBack) {
        ApiClient.create().getPartners(type).enqueue(new Callback<PartnersResponseModel>() {
            @Override
            public void onResponse(Call<PartnersResponseModel> call, Response<PartnersResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }
            @Override
            public void onFailure(Call<PartnersResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}

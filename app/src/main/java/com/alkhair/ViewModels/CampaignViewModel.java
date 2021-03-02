package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignViewModel extends AndroidViewModel {
    public CampaignViewModel(@NonNull Application application) {

        super(application);
    }

    public void getCampaign(final GetCallBack callBack) {
        ApiClient.create().getCampaign().enqueue(new Callback<ProjectDetailsResponseModel>() {
            @Override
            public void onResponse(Call<ProjectDetailsResponseModel> call, Response<ProjectDetailsResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<ProjectDetailsResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}


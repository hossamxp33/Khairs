package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.CommentRequestModel;
import com.alkhair.Models.CommentResponseModel;
import com.alkhair.Models.ContactUsDataResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public  class ContatcUSViewModel extends AndroidViewModel {
    public ContatcUSViewModel(@NonNull Application application) {

        super(application);
    }

    public void postComment (CommentRequestModel request, final GetCallBack callBack) {
        ApiClient.create().postComment(request).enqueue(new Callback<CommentResponseModel>() {
            @Override
            public void onResponse(Call<CommentResponseModel> call, Response<CommentResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }


            public void onFailure(Call<CommentResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }


    public void data ( final GetCallBack callBack) {
        ApiClient.create().contactData( ).enqueue(new Callback<ContactUsDataResponseModel>() {
            @Override
            public void onResponse(Call<ContactUsDataResponseModel> call, Response<ContactUsDataResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            public void onFailure(Call<ContactUsDataResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}

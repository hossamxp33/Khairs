package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.LoginResponseModel;
import com.alkhair.Models.RegistrationResponseModel;
import com.alkhair.Models.RegistrationequestModel;
import com.alkhair.Models.ResetPasswordResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    //register
    public void Registration(RegistrationequestModel request, final GetCallBack callBack) {
        ApiClient.create().registration(request).enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }

    //login
    public void Login(String email, String password, final GetCallBack callBack) {
        ApiClient.create().login(email, password).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }

    // reset password
     public void reset (String email,  final GetCallBack callBack) {
        ApiClient.create().resetPassword(email).enqueue(new Callback<ResetPasswordResponseModel>() {
            @Override
            public void onResponse(Call<ResetPasswordResponseModel> call, Response<ResetPasswordResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }
            @Override
            public void onFailure(Call<ResetPasswordResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}

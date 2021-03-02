package com.alkhair.ViewModels;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.alkhair.Models.DonationsHistoryRespopnse;
import com.alkhair.Models.PaymentWaysResponse;
 import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonationsViewModel extends AndroidViewModel {
    public DonationsViewModel(@NonNull Application application) {

        super(application);
    }

    public void getPayment (final GetCallBack callBack) {
        ApiClient.create().getways().enqueue(new Callback<PaymentWaysResponse>() {
            @Override
            public void onResponse(Call<PaymentWaysResponse> call, Response<PaymentWaysResponse> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<PaymentWaysResponse> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }

    public void getDonationsHistory (String id ,final GetCallBack callBack) {
        ApiClient.create().getDonationsHistory(id).enqueue(new Callback<DonationsHistoryRespopnse>() {
            @Override
            public void onResponse(Call<DonationsHistoryRespopnse> call, Response<DonationsHistoryRespopnse> response) {
                if (response.isSuccessful()) {
                    Log.v("test","sucess");
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    Log.v("test","faile");
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<DonationsHistoryRespopnse> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}

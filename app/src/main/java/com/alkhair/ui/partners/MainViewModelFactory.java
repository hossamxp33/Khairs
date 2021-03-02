package com.alkhair.ui.partners;

import android.app.Application;

import com.alkhair.helper.services.webApi.ApiClient;
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel;
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory implements ViewModelProvider.Factory {


    private Application application;


    public MainViewModelFactory(Application application1) {
        application = application1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == MainViewModel.class)
        {
            return (T) new MainViewModel(getApiService());
        }


        throw new IllegalArgumentException("Invalid view model class type");
    }


    private APIServices getApiService() {
        return ApiClient.getClient().create(APIServices.class);
    }

}

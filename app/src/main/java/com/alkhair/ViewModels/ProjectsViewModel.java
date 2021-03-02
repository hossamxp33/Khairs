package com.alkhair.ViewModels;

import android.app.Application;

import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.Models.ProjecttypesResponseModel;
import com.alkhair.helper.interfaces.GetCallBack;
import com.alkhair.helper.services.webApi.ApiClient;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsViewModel extends AndroidViewModel {
    public ProjectsViewModel(@NonNull Application application) {

        super(application);
    }

    public LiveData<ProjectDetailsResponseModel> getProjectDetails ( String id ) {

        final MutableLiveData<ProjectDetailsResponseModel> data = new MutableLiveData<>();
        ApiClient.create().getProjectDetails( id).enqueue(new Callback<ProjectDetailsResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ProjectDetailsResponseModel> call, @NotNull Response<ProjectDetailsResponseModel> response) {

                data.setValue(response.body());

            }

            @Override
            public void onFailure(@NotNull Call<ProjectDetailsResponseModel> call, @NotNull Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public void getProjectstypes (final GetCallBack callBack) {
        ApiClient.create().getProjectTypes().enqueue(new Callback<ProjecttypesResponseModel>() {
            @Override
            public void onResponse(Call<ProjecttypesResponseModel> call, Response<ProjecttypesResponseModel> response) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body());
                } else {
                    callBack.getCallBack(false, response.code(), response.body());
                }
            }

            @Override
            public void onFailure(Call<ProjecttypesResponseModel> call, Throwable t) {
                callBack.getCallBack(false, 1, t);
            }
        });
    }
}
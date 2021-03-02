package com.alkhair.ui.partners;

import android.annotation.SuppressLint;
import android.util.Log;

import com.alkhair.Models.OurPartner;
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


class MainFragmentViewModel extends ViewModel {


    public MutableLiveData<OurPartner> mainViewMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private APIServices serverGateway;

     MainFragmentViewModel(APIServices serverGateway1) {
        serverGateway = serverGateway1;
        getData();
    }

    public void getData() {
        getObservable().subscribeWith(getObserver());
    }


    @SuppressLint("CheckResult")
    private Observable<OurPartner> getObservable() {
        Observable<OurPartner> photographersData = serverGateway.GetPartnerData();
        photographersData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return photographersData;
    }

    private DisposableObserver<OurPartner> getObserver() {
        return new DisposableObserver<OurPartner>() {
            @Override
            public void onNext(@NonNull OurPartner result) {
                if (mainViewMutableLiveData !=null)
                mainViewMutableLiveData.postValue(result);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Errors", "Error" + e);
                e.printStackTrace();
                throwableMutableLiveData.postValue(e);
            }
            @Override
            public void onComplete() {
            }
        };
    }
}

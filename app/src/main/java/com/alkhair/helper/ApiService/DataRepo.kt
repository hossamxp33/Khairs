package com.codesroots.mac.firstkotlon.DataLayer.Repo

import android.annotation.SuppressLint
import android.transition.Slide
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alkhair.Models.*
import com.alkhair.helper.services.webApi.ApiClient
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class  DataRepo {



    //Get_PartnerData
    fun Get_PartnerData(livedata: MutableLiveData<List<OurPartnerResult>>?) {

        getServergetway().GetPartnerData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { data -> data }
            .subscribe(
                { books ->
                    livedata?.postValue(books.result)
                },
                { error ->

                }
            )
    }
    //Get_PartnerData
    fun  GetChairtyDetails(id: Int,livedata: MutableLiveData<Result>?) {

        getServergetway().GetChairtyDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { data -> data }
                .subscribe(
                        { books ->
                            livedata?.postValue(books.result)
                        },
                        { error ->

                        }
                )
    }

    //GGetProjectsDetails
    fun GetProjectsDetails(id: Int,livedata: MutableLiveData<ProjectDetailsResponseModel>?) {
        getServergetway().GetProjectsDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { data -> data }
                .subscribe(
                        { books ->
                            livedata?.postValue(books)
                        },
                        { error ->

                        }
                )
    }
    fun GetProjectsTypes(livedata: MutableLiveData<ProjecttypesResponseModel>?) {
        getServergetway().GetProjectsTypes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { data -> data }
                .subscribe(
                        { books ->
                            livedata?.postValue(books)
                        },
                        { error ->

                        }
                )
    }
    fun GetSlider(livedata: MutableLiveData<SliderData>?) {
        getServergetway().GetSlider()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { data -> data }
                .subscribe(
                        { books ->
                            livedata?.postValue(books)
                        },
                        { error ->

                        }
                )
    }


    @SuppressLint("CheckResult")
    fun getServergetway () : APIServices
    {
        return ApiClient.getClient().create(APIServices::class.java)
    }
}


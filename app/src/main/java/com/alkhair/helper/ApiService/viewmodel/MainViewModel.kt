package com.codesroots.mac.cards.presentaion.mainfragment.viewmodel

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide

import com.codesroots.mac.firstkotlon.DataLayer.Repo.DataRepo
import io.reactivex.disposables.CompositeDisposable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.BaseObservable
import android.graphics.Color
import com.alkhair.Models.*
import com.codesroots.mac.firstkotlon.DataLayer.ApiService.APIServices


@BindingAdapter("text_color")
/////// set Stock image statue /////
fun setTextStock(text: TextView, color: String?) {
    when (color){
        "-1" ->
         text.setTextColor(Color.parseColor("#ef1919"))


        "1" ->           text.setTextColor(Color.parseColor("#1E75DE"))

        else -> { // Note the block
            // Display an image on image view from drawable
            text.setTextColor(Color.parseColor("#9C9898"))
        }
    }

}
@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
@BindingAdapter("app:imageResourcee")
fun setImageResourcee(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}


public class MainViewModel(apiService: APIServices) : ViewModel() {

    var DateRepoCompnay: DataRepo = DataRepo()
     var mCompositeDisposable = CompositeDisposable()
    var loadingLivedat: MutableLiveData<Boolean> = MutableLiveData()


    var PartnerResponseLD : MutableLiveData<List<OurPartnerResult>>? = null
    var ChairtyDetailsResponseLD : MutableLiveData<Result>? = null
    var ProjectsDetailsResponseLD : MutableLiveData<ProjectDetailsResponseModel>? = null
    var   ProjectsTypesResponseLD: MutableLiveData<ProjecttypesResponseModel>? = null
    var   SliderResponseLD: MutableLiveData<SliderData>? = null

    init {

        PartnerResponseLD = MutableLiveData()
        ChairtyDetailsResponseLD = MutableLiveData()
        ProjectsDetailsResponseLD = MutableLiveData()
        ProjectsTypesResponseLD = MutableLiveData()
        SliderResponseLD= MutableLiveData()
    }



  fun  get_PartnerData(){
    DateRepoCompnay.Get_PartnerData(PartnerResponseLD)
}
    fun  get_ChairtyDetails(id: Int){
        DateRepoCompnay.GetChairtyDetails(id,ChairtyDetailsResponseLD)
    }
    fun  GetProjectsDetails(id: Int){
        DateRepoCompnay.GetProjectsDetails(id,ProjectsDetailsResponseLD)
    }

    fun  GetProjectsTypes(){
        DateRepoCompnay.GetProjectsTypes(ProjectsTypesResponseLD)
    }
    fun  GetSlider(){
        DateRepoCompnay.GetSlider(SliderResponseLD)
    }

    
    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
        mCompositeDisposable.clear()

    }
}
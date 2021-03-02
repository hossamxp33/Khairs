package com.alkhair.Models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Result (
        val CharityAbout_Ar: String,
        val CharityAbout_En: Any,
        val CharityAddress_Ar: String,
        val CharityAddress_En: Any,
        val CharityContactEmail: Any,
        val CharityFeedback: CharityFeedback,
        val CharityId: Int,
        val CharityLatitude: Any,
        val CharityLogo: String,
        val CharityLongitude: Any,
        val CharityMission_Ar: String,
        val CharityMission_En: String,
        val CharityName_Ar: String,
        val CharityName_En: Any,
        val CharityNewsList: Any,
        val CharityPhone1: String,
        val CharityPhone2: String,
        val CharityProjectsList: List<ProjectDetailsResponseModel.ResultBean>,
        val CharityType: Int,
        val CharityVision_Ar: String,
        val CharityVision_En: String,
        val CharityWhatsappNumber: Any
) : Serializable{

}

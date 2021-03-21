package com.alkhair.Models

import android.os.Parcelable
import java.io.Serializable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Result (
        val CharityAbout_Ar: String,
        val CharityAbout_En: String,
        val CharityAddress_Ar: String,
        val CharityAddress_En: String,
        val CharityContactEmail: String,
        val CharityFeedback: CharityFeedback,
        val CharityId: Int,
        val CharityLatitude: Double,
        val CharityLogo: String,
        val CharityLongitude: Double,
        val CharityMission_Ar: String,
        val CharityMission_En: String,
        val CharityName_Ar: String,
        val CharityName_En: String,
        val CharityPhone1: String,
        val CharityPhone2: String,
        val CharityProjectsList: List<ProjectDetailsResponseModel.ResultBean>,
        val CharityType: Int,
        val CharityVision_Ar: String,
        val CharityVision_En: String
): Parcelable {};

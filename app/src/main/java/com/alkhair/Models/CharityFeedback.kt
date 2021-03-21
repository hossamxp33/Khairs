package com.alkhair.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class CharityFeedback(
    val Email: String,
    val FeedbackId: Int,
    val KuwaitCharityid: Int,
    val MessageDetails: String,
    val Name: String,
    val Status: Int,
    val SubmittedOn: String
): Parcelable {};

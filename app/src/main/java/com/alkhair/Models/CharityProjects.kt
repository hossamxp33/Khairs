package com.alkhair.Models

import java.io.Serializable

data class CharityProjects(
    val CharityId: Int,
    val CollectedAmount: Int,
    val ProjectDescription_Ar: String,
    val ProjectDescription_En: String,
    val ProjectId: Int,
    val ProjectName_Ar: String,
    val ProjectName_En: String,
    val ProjectTypeId: Int,
    val TargetedAmount: Int
) : Serializable {

}
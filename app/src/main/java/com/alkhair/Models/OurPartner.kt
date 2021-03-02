package com.alkhair.Models

data class OurPartner(
    val result: List<OurPartnerResult>,
    val success: String
)
data class OurPartnerResult(
        val CharityId: Int,
        val CharityLogoPath: String,
        val CharityName_Ar: String,
        val CharityName_En: String
)
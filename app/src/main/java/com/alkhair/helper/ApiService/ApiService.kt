package com.codesroots.mac.firstkotlon.DataLayer.ApiService

import com.alkhair.Models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface APIServices {

    @GET("http://test.e.net.kw/KuwaitAlKhairAPI/API/Charity/GetPartnerLogos?charityType=0")/*{company_id}*/
    fun GetPartnerData(): Observable<OurPartner>


    @GET("http://test.e.net.kw/kuwaitalkhairapi/api/charity/GetSpecificCharityDetails")/*{company_id}*/
    fun GetChairtyDetails(@Query("charityid")id: Int):
            Observable<ChairtyDetails>

    @GET("http://test.e.net.kw/KuwaitAlKhairAPI/API/PROJECT/GetAllProjects")/*{company_id}*/
    fun GetProjectsDetails(@Query("projectCategoryID")id: Int):
            Observable<ProjectDetailsResponseModel>


    @GET("http://test.e.net.kw/KuwaitAlKhairAPI/API/PROJECT/GetProjectTypes")/*{company_id}*/
    fun GetProjectsTypes():
            Observable<ProjecttypesResponseModel>

    @GET("http://test.e.net.kw/kuwaitalkhairapi/api/home/GetBannerImages?bannerNumber=1")/*{company_id}*/
    fun GetSlider():
            Observable<SliderData>




}



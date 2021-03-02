package com.alkhair.helper.services.webApi;

import android.database.Observable;

import com.alkhair.Models.AboutResponse;
import com.alkhair.Models.CampaginResponseModel;
import com.alkhair.Models.CommentRequestModel;
import com.alkhair.Models.CommentResponseModel;
import com.alkhair.Models.ContactUsDataResponseModel;
import com.alkhair.Models.DonationsHistoryRespopnse;
import com.alkhair.Models.LoginResponseModel;
import com.alkhair.Models.NewsResponseModel;
import com.alkhair.Models.OurPartner;
import com.alkhair.Models.PartnersResponseModel;
import com.alkhair.Models.PaymentWaysResponse;
import com.alkhair.Models.ProjectDetailsResponseModel;
import com.alkhair.Models.ProjecttypesResponseModel;
import com.alkhair.Models.RegistrationResponseModel;
import com.alkhair.Models.RegistrationequestModel;
import com.alkhair.Models.ResetPasswordResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Hossam on 11/19/2020.
 */
public interface ApiInterface {
    // campaign
    @GET(StoreService.Campaigns)
    Call<ProjectDetailsResponseModel> getCampaign();
    // projects
    @GET(StoreService.projectDetails)
    Call<ProjectDetailsResponseModel> getProjectDetails(@Query("projectCategoryID") String id );
    @GET(StoreService.projectTypes)
    Call<ProjecttypesResponseModel> getProjectTypes ();
    // news
    @GET(StoreService.newsList)
    Call<NewsResponseModel> getNewsList ();
    // post comment (contact us)
    @POST(StoreService.contactus)
    Call<CommentResponseModel> postComment (@Body CommentRequestModel request);
    @GET(StoreService.Contact_us_data)
    Call<ContactUsDataResponseModel> contactData ();
    //registration
    @POST(StoreService.registration)
    Call<RegistrationResponseModel> registration  (@Body RegistrationequestModel request);
    //login
    @GET(StoreService.login)
    Call<LoginResponseModel> login  (@Query("userEmail") String email, @Query("password") String password);
    // reset password
    @GET(StoreService.forgetPassword)
    Call<ResetPasswordResponseModel> resetPassword  (@Query("userEmail") String email );
    // partners
    @GET(StoreService.partners)
    Call<PartnersResponseModel> getPartners(@Query("charityType") String type);
    // About kuwait
    @GET(StoreService.aboutKuwaitAlhair)
    Call<AboutResponse> getAbout ();
    // payment
    @GET(StoreService.paymentWays)
    Call<PaymentWaysResponse> getways();
    // my donations
    @GET(StoreService.my_donations)
    Call<DonationsHistoryRespopnse> getDonationsHistory(@Query("customerid") String id);



}

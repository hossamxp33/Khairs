package com.alkhair.helper.services.webApi;

interface StoreService {
    String Base_Url = "http://test.e.net.kw/KuwaitAlKhairAPI/API/";
    String Campaigns ="campaigns/GetAllCampaigns/";
    String projectDetails ="PROJECT/GetAllProjects?projectCategoryID";
    String projectTypes="PROJECT/GetProjectTypes";
    String newsList="news/getnewslist/";
    String contactus="home/PostCommentsForKuwaitCharity/";
    String registration ="Home/PostNewUserRegistration/";
    String login ="Home/GetCustomerInformation";
    String forgetPassword ="Home/GetResetPasswordResult";
    String Contact_us_data = "home/GetKuwaitAlKhairContactDetails";
    String partners = "Charity/GetPartnerLogos?charityType";
    String aboutKuwaitAlhair ="Home/GetAboutUsContent";
    String my_donations = "paymentinformation/GetPaymenthistory";
    String paymentWays ="paymentinformation/GetPaymentOptions/";
    String ourPartner ="Charity/GetPartnerLogos?charityType=0";

}

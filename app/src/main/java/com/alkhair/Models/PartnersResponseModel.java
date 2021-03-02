package com.alkhair.Models;

import java.util.List;


public class PartnersResponseModel {


    /**
     * success : true
     * result : [{"CharityId":1,"CharityName_Ar":"أوقاف","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/partners01-item_0.png"},{"CharityId":2,"CharityName_Ar":"جمعية شمر الخيرية","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/partners02_item_0.png"},{"CharityId":3,"CharityName_Ar":"مشاريع التبرع","CharityName_En":"Charity3","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/partners03_item_0.png"},{"CharityId":4,"CharityName_Ar":"جمعية النجاة الخيرية","CharityName_En":"Charity4","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities05_item_0.png"},{"CharityId":5,"CharityName_Ar":"المنبر دورانية سيسيتي","CharityName_En":"Charity5","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/Committees01_item_0.png"},{"CharityId":6,"CharityName_Ar":"جمعية ايات الخيرية","CharityName_En":"Charity6","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities06_item_0.png"},{"CharityId":7,"CharityName_Ar":"جمعية النوري الخيرية","CharityName_En":"Al Nouri Charity ","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities04_item_0.png"},{"CharityId":8,"CharityName_Ar":"الهيئة الخيرية الإسلامية العالمية","CharityName_En":"International Islamic Charity Organization","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities03_item_0.png"},{"CharityId":9,"CharityName_Ar":"زكاة نماء","CharityName_En":"Namaa Zakat","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities02_item_0.png"},{"CharityId":10,"CharityName_Ar":"جمعية التكافل الخيرية","CharityName_En":"Al Takaful Charity","CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/charities01_item_0.png"},{"CharityId":11,"CharityName_Ar":"krcs charity","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/161.png"},{"CharityId":12,"CharityName_Ar":"المنابر الخيرية","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/AlManabr.png"},{"CharityId":13,"CharityName_Ar":"testfileupload","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/hamla01.jpg"},{"CharityId":14,"CharityName_Ar":"test2","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/200.png"},{"CharityId":16,"CharityName_Ar":"testcharity","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/193.png"},{"CharityId":17,"CharityName_Ar":"testcharity","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/254.png"},{"CharityId":18,"CharityName_Ar":"testcharitydetails","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/236.png"},{"CharityId":19,"CharityName_Ar":"نماء للزكاة والتنمية المجتمعية","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/Nemaa.jpg"},{"CharityId":20,"CharityName_Ar":"testmap","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/197.png"},{"CharityId":21,"CharityName_Ar":"test font","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/215.png"},{"CharityId":22,"CharityName_Ar":"testhtml","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/218.png"},{"CharityId":23,"CharityName_Ar":"TestProdn","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/193.png"},{"CharityId":24,"CharityName_Ar":"TestInProdn","CharityName_En":null,"CharityLogoPath":"http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/180.png"}]
     */

    private String success;
    private List<ResultBean> result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * CharityId : 1
         * CharityName_Ar : أوقاف
         * CharityName_En : null
         * CharityLogoPath : http://test.e.net.kw/kuwaitcharityadmin/Images/CharityLogos/partners01-item_0.png
         */

        private Integer CharityId;
        private String CharityName_Ar;
        private Object CharityName_En;
        private String CharityLogoPath;

        public Integer getCharityId() {
            return CharityId;
        }

        public void setCharityId(Integer charityId) {
            CharityId = charityId;
        }

        public String getCharityName_Ar() {
            return CharityName_Ar;
        }

        public void setCharityName_Ar(String charityName_Ar) {
            CharityName_Ar = charityName_Ar;
        }

        public Object getCharityName_En() {
            return CharityName_En;
        }

        public void setCharityName_En(Object charityName_En) {
            CharityName_En = charityName_En;
        }

        public String getCharityLogoPath() {
            return CharityLogoPath;
        }

        public void setCharityLogoPath(String charityLogoPath) {
            CharityLogoPath = charityLogoPath;
        }
    }
}

package com.alkhair.Models;

import java.io.Serializable;
import java.util.List;

public class ProjecttypesResponseModel {


    /**
     * success : true
     * result : [{"ID":1,"Name_en":"Mosques","Name_ar":"مساجد","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Mosques1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Mosques.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Mosques1.png"},{"ID":2,"Name_en":"Wells","Name_ar":"حفر الآبار","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Wells1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Wells.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Wells1.png"},{"ID":4,"Name_en":"Health centers","Name_ar":"مراكز صحية","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Healthcenters1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/HealthCenters.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Healthcenters1.png"},{"ID":5,"Name_en":"Schools","Name_ar":"بناء مدارس","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Schools1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Schools.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Schools1.png"},{"ID":6,"Name_en":"Relief","Name_ar":"الاغاثة","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Relief1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Relief.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Relief1.png"},{"ID":7,"Name_en":"ProductriveFamilies","Name_ar":"الأسر المنتجة","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/ProductriveFamilies1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/ProductiveFamilies.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/ProductriveFamilies1.png"},{"ID":8,"Name_en":"\r\nCharitable endowment","Name_ar":"الوقف الخيري","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Charitableendowment1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Charitableendowment.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Charitableendowment1.png"},{"ID":9,"Name_en":"EducationSupport","Name_ar":"دعم التعليم","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/EducationSupport1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/EducationSupport.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/EducationSupport1.png"},{"ID":10,"Name_en":"HealthCare","Name_ar":"رعاية صحية","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/HealthCare1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/HealthCenters.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/HealthCare1.png"},{"ID":11,"Name_en":"Orphanages","Name_ar":"دور الأيتام","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Orphanages1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Orphanages.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Orphanages1.png"},{"ID":12,"Name_en":"PublicContributions","Name_ar":"مساهمات عامة","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PublicContributions1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/PublicContributions.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PublicContributions1.png"},{"ID":13,"Name_en":"PublicProjects","Name_ar":"مشاريع عامة","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PublicProjects1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/PublicProjects.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PublicProjects1.png"},{"ID":14,"Name_en":"MemorizationCenters","Name_ar":"مراكز تحفيظ","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/MemorizationCenters1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/MemorizationCenters.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/MemorizationCenters1.png"},{"ID":15,"Name_en":"PoorHousing","Name_ar":"مساكن فقراء","logo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PoorHousing1.jpg","ProjectCategoryIcon":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/PoorHousing.svg","ProjectViewLogo":"http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/PoorHousing1.png"}]
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

    public static class ResultBean implements Serializable {
        /**
         * ID : 1
         * Name_en : Mosques
         * Name_ar : مساجد
         * logo : http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Mosques1.jpg
         * ProjectCategoryIcon : http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/NewCharityProjectCategories/Mosques.svg
         * ProjectViewLogo : http://test.e.net.kw/kuwaitalkhairwebsite/Content/images/ProjectCategories/Mosques1.png
         */

        private Integer ID;
        private String Name_en;
        private String Name_ar;
        private String logo;
        private String ProjectCategoryIcon;
        private String ProjectViewLogo;

        public Integer getID() {
            return ID;
        }

        public void setID(Integer ID) {
            this.ID = ID;
        }

        public String getName_en() {
            return Name_en;
        }

        public void setName_en(String name_en) {
            Name_en = name_en;
        }

        public String getName_ar() {
            return Name_ar;
        }

        public void setName_ar(String name_ar) {
            Name_ar = name_ar;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getProjectCategoryIcon() {
            return ProjectCategoryIcon;
        }

        public void setProjectCategoryIcon(String projectCategoryIcon) {
            ProjectCategoryIcon = projectCategoryIcon;
        }

        public String getProjectViewLogo() {
            return ProjectViewLogo;
        }

        public void setProjectViewLogo(String projectViewLogo) {
            ProjectViewLogo = projectViewLogo;
        }
    }
}

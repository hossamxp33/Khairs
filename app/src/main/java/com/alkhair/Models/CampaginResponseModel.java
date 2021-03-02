package com.alkhair.Models;


import java.io.Serializable;
import java.util.List;


public class CampaginResponseModel {

    /**
     * success : true
     * result : [{"CharityId":1,"CharityName_En":null,"CharityName_Ar":"أوقاف","ProjectId":79,"ProjectName_En":null,"ProjectName_Ar":"Test Campaign 1","ProjectTypeId":1,"ProjectDescription_En":null,"ProjectDescription_Ar":"Test Campaign 1description","TargetedAmount":563,"CollectedAmount":12,"CollectedByOther":null,"MediaId":106,"MediaPath":"http://test.e.net.kw/kuwaitcharityadmin/MediaUploads/CampaignImages/79_hamla03_item_0.jpg","MediaType":1},{"CharityId":1,"CharityName_En":null,"CharityName_Ar":"أوقاف","ProjectId":80,"ProjectName_En":null,"ProjectName_Ar":"Test Campaign 2","ProjectTypeId":1,"ProjectDescription_En":null,"ProjectDescription_Ar":"Test Campaign 2description","TargetedAmount":6320,"CollectedAmount":96,"CollectedByOther":null,"MediaId":107,"MediaPath":"http://test.e.net.kw/kuwaitcharityadmin/MediaUploads/CampaignImages/80_hamla02_item_0.jpg","MediaType":1},{"CharityId":1,"CharityName_En":null,"CharityName_Ar":"أوقاف","ProjectId":81,"ProjectName_En":null,"ProjectName_Ar":"Test Campaign 3","ProjectTypeId":1,"ProjectDescription_En":null,"ProjectDescription_Ar":"Test Campaign 3desc","TargetedAmount":369123,"CollectedAmount":2354,"CollectedByOther":null,"MediaId":108,"MediaPath":"http://test.e.net.kw/kuwaitcharityadmin/MediaUploads/CampaignImages/81_13_hamla01_item_0_item_0.jpg","MediaType":1},{"CharityId":1,"CharityName_En":null,"CharityName_Ar":"أوقاف","ProjectId":81,"ProjectName_En":null,"ProjectName_Ar":"Test Campaign 3","ProjectTypeId":1,"ProjectDescription_En":null,"ProjectDescription_Ar":"Test Campaign 3desc","TargetedAmount":369123,"CollectedAmount":2354,"CollectedByOther":null,"MediaId":109,"MediaPath":"http://test.e.net.kw/kuwaitcharityadmin/MediaUploads/Videos/81_SampleVideo_1280x720_1mb.mp4","MediaType":2}]
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
         * CharityId : 1
         * CharityName_En : null
         * CharityName_Ar : أوقاف
         * ProjectId : 79
         * ProjectName_En : null
         * ProjectName_Ar : Test Campaign 1
         * ProjectTypeId : 1
         * ProjectDescription_En : null
         * ProjectDescription_Ar : Test Campaign 1description
         * TargetedAmount : 563.0
         * CollectedAmount : 12.0
         * CollectedByOther : null
         * MediaId : 106
         * MediaPath : http://test.e.net.kw/kuwaitcharityadmin/MediaUploads/CampaignImages/79_hamla03_item_0.jpg
         * MediaType : 1
         */

        private Integer CharityId;
        private String CharityName_En;
        private String CharityName_Ar;
        private Integer ProjectId;
        private String ProjectName_En;
        private String ProjectName_Ar;
        private Integer ProjectTypeId;
        private String ProjectDescription_En;
        private String ProjectDescription_Ar;
        private Double TargetedAmount;
        private Double CollectedAmount;
        private Object CollectedByOther;
        private Integer MediaId;
        private String MediaPath;
        private Integer MediaType;

        public Integer getCharityId() {
            return CharityId;
        }

        public void setCharityId(Integer charityId) {
            CharityId = charityId;
        }

        public String getCharityName_En() {
            return CharityName_En;
        }

        public void setCharityName_En(String charityName_En) {
            CharityName_En = charityName_En;
        }

        public String getCharityName_Ar() {
            return CharityName_Ar;
        }

        public void setCharityName_Ar(String charityName_Ar) {
            CharityName_Ar = charityName_Ar;
        }

        public Integer getProjectId() {
            return ProjectId;
        }

        public void setProjectId(Integer projectId) {
            ProjectId = projectId;
        }

        public String getProjectName_En() {
            return ProjectName_En;
        }

        public void setProjectName_En(String projectName_En) {
            ProjectName_En = projectName_En;
        }

        public String getProjectName_Ar() {
            return ProjectName_Ar;
        }

        public void setProjectName_Ar(String projectName_Ar) {
            ProjectName_Ar = projectName_Ar;
        }

        public Integer getProjectTypeId() {
            return ProjectTypeId;
        }

        public void setProjectTypeId(Integer projectTypeId) {
            ProjectTypeId = projectTypeId;
        }

        public String getProjectDescription_En() {
            return ProjectDescription_En;
        }

        public void setProjectDescription_En(String projectDescription_En) {
            ProjectDescription_En = projectDescription_En;
        }

        public String getProjectDescription_Ar() {
            return ProjectDescription_Ar;
        }

        public void setProjectDescription_Ar(String projectDescription_Ar) {
            ProjectDescription_Ar = projectDescription_Ar;
        }

        public Double getTargetedAmount() {
            return TargetedAmount;
        }

        public void setTargetedAmount(Double targetedAmount) {
            TargetedAmount = targetedAmount;
        }

        public Double getCollectedAmount() {
            return CollectedAmount;
        }

        public void setCollectedAmount(Double collectedAmount) {
            CollectedAmount = collectedAmount;
        }

        public Object getCollectedByOther() {
            return CollectedByOther;
        }

        public void setCollectedByOther(Object collectedByOther) {
            CollectedByOther = collectedByOther;
        }

        public Integer getMediaId() {
            return MediaId;
        }

        public void setMediaId(Integer mediaId) {
            MediaId = mediaId;
        }

        public String getMediaPath() {
            return MediaPath;
        }

        public void setMediaPath(String mediaPath) {
            MediaPath = mediaPath;
        }

        public Integer getMediaType() {
            return MediaType;
        }

        public void setMediaType(Integer mediaType) {
            MediaType = mediaType;
        }
    }
}

package com.alkhair.Models;

import java.io.Serializable;
import java.util.List;


public class NewsResponseModel {


    /**
     * success : true
     * result : [{"NewsId":1,"CharityId":1,"NewsImagePath":"hamla04.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"إيماناً منا بأهمية ودور القطاع ","NewsShortDescription_En":null,"NewsShortDescription_Ar":"إيماناً منا بأهمية ودور القطاع الخاص في الإسهام الفعال في خدمة المجتمع، ونظرا لحاجة القطاع الخيري وبعض الجهات العاملة فيه إلى تطوير آليات مشاريع","NewsLongDescription_En":null,"NewsLongDescription_Ar":"يماناً منا بأهمية ودور القطاع الخاص في الإسهام الفعال في خدمة المجتمع، ونظرا لحاجة القطاع الخيري وبعض الجهات العاملة فيه إلى تطوير آليات مشاريع العمل الخيري، من هنا جاءت فكرة منصة كويت الخير، لتأطير أعمالها وبرامجها وأنشطتها الخيرية وفق نظام آلي شامل يحقق التعاون والتكامل ليكون أو نظام إلكتروني يربط جميع الهيئات الخيرية في دولة الكويت تحت مظلة واحدة."},{"NewsId":3,"CharityId":1,"NewsImagePath":"hamla04-item_0.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"إيماناً منا بأهمية ودور القطاع الخاص في الإسهام","NewsShortDescription_En":null,"NewsShortDescription_Ar":"test image size","NewsLongDescription_En":null,"NewsLongDescription_Ar":"test image size"},{"NewsId":4,"CharityId":1,"NewsImagePath":"hamla01-item_0.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"test update - 02 ","NewsShortDescription_En":null,"NewsShortDescription_Ar":"Test Upload","NewsLongDescription_En":null,"NewsLongDescription_Ar":"Test Upload"},{"NewsId":5,"CharityId":1,"NewsImagePath":"net-item_0.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"test update - 03 ","NewsShortDescription_En":null,"NewsShortDescription_Ar":"Test image width","NewsLongDescription_En":null,"NewsLongDescription_Ar":"Test image width"},{"NewsId":6,"CharityId":1,"NewsImagePath":"cnet-item_0.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"test update - 12323 ","NewsShortDescription_En":null,"NewsShortDescription_Ar":"Test Image Update 234234","NewsLongDescription_En":null,"NewsLongDescription_Ar":"Test Image Update 234234"},{"NewsId":7,"CharityId":11,"NewsImagePath":"video02-item_0.jpg","NewsHeadline_En":null,"NewsHeadline_Ar":"Test News Item for Charity 1","NewsShortDescription_En":null,"NewsShortDescription_Ar":"Test News Item for Charity 1 short description","NewsLongDescription_En":null,"NewsLongDescription_Ar":"Test News Item for Charity 1 long description"}]
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
         * NewsId : 1
         * CharityId : 1
         * NewsImagePath : hamla04.jpg
         * NewsHeadline_En : null
         * NewsHeadline_Ar : إيماناً منا بأهمية ودور القطاع
         * NewsShortDescription_En : null
         * NewsShortDescription_Ar : إيماناً منا بأهمية ودور القطاع الخاص في الإسهام الفعال في خدمة المجتمع، ونظرا لحاجة القطاع الخيري وبعض الجهات العاملة فيه إلى تطوير آليات مشاريع
         * NewsLongDescription_En : null
         * NewsLongDescription_Ar : يماناً منا بأهمية ودور القطاع الخاص في الإسهام الفعال في خدمة المجتمع، ونظرا لحاجة القطاع الخيري وبعض الجهات العاملة فيه إلى تطوير آليات مشاريع العمل الخيري، من هنا جاءت فكرة منصة كويت الخير، لتأطير أعمالها وبرامجها وأنشطتها الخيرية وفق نظام آلي شامل يحقق التعاون والتكامل ليكون أو نظام إلكتروني يربط جميع الهيئات الخيرية في دولة الكويت تحت مظلة واحدة.
         */

        private Integer NewsId;
        private Integer CharityId;
        private String NewsImagePath;
        private Object NewsHeadline_En;
        private String NewsHeadline_Ar;
        private Object NewsShortDescription_En;
        private String NewsShortDescription_Ar;
        private Object NewsLongDescription_En;
        private String NewsLongDescription_Ar;

        public Integer getNewsId() {
            return NewsId;
        }

        public void setNewsId(Integer newsId) {
            NewsId = newsId;
        }

        public Integer getCharityId() {
            return CharityId;
        }

        public void setCharityId(Integer charityId) {
            CharityId = charityId;
        }

        public String getNewsImagePath() {
            return NewsImagePath;
        }

        public void setNewsImagePath(String newsImagePath) {
            NewsImagePath = newsImagePath;
        }

        public Object getNewsHeadline_En() {
            return NewsHeadline_En;
        }

        public void setNewsHeadline_En(Object newsHeadline_En) {
            NewsHeadline_En = newsHeadline_En;
        }

        public String getNewsHeadline_Ar() {
            return NewsHeadline_Ar;
        }

        public void setNewsHeadline_Ar(String newsHeadline_Ar) {
            NewsHeadline_Ar = newsHeadline_Ar;
        }

        public Object getNewsShortDescription_En() {
            return NewsShortDescription_En;
        }

        public void setNewsShortDescription_En(Object newsShortDescription_En) {
            NewsShortDescription_En = newsShortDescription_En;
        }

        public String getNewsShortDescription_Ar() {
            return NewsShortDescription_Ar;
        }

        public void setNewsShortDescription_Ar(String newsShortDescription_Ar) {
            NewsShortDescription_Ar = newsShortDescription_Ar;
        }

        public Object getNewsLongDescription_En() {
            return NewsLongDescription_En;
        }

        public void setNewsLongDescription_En(Object newsLongDescription_En) {
            NewsLongDescription_En = newsLongDescription_En;
        }

        public String getNewsLongDescription_Ar() {
            return NewsLongDescription_Ar;
        }

        public void setNewsLongDescription_Ar(String newsLongDescription_Ar) {
            NewsLongDescription_Ar = newsLongDescription_Ar;
        }
    }
}

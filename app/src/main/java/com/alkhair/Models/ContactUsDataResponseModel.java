package com.alkhair.Models;


public class ContactUsDataResponseModel {


    /**
     * success : true
     * result : {"CompanyAddress":" الشرق \u2013 شارع أحمد المبارك \u2013 مجمع بهبهاني \u2013 مجمع بهبهاني","ContactNumbersWithExtn":"3/2/1/ 22474620 965+","PrimaryContactNo":"22474618 965+","PrimaryEmailAddress":"info@kuwaitalkhair.com","KuwaitAlKhairSocialMediaHandles":{"FacebookLink":"FacebookLinkPlaceholder","TwitterLink":"TwitterLinkPlaceholder","YouTubeLink":"YouTubeLinkPlaceholder","InstagramLink":"InstagramLinkPlaceholder"}}
     */

    private String success;
    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * CompanyAddress :  الشرق – شارع أحمد المبارك – مجمع بهبهاني – مجمع بهبهاني
         * ContactNumbersWithExtn : 3/2/1/ 22474620 965+
         * PrimaryContactNo : 22474618 965+
         * PrimaryEmailAddress : info@kuwaitalkhair.com
         * KuwaitAlKhairSocialMediaHandles : {"FacebookLink":"FacebookLinkPlaceholder","TwitterLink":"TwitterLinkPlaceholder","YouTubeLink":"YouTubeLinkPlaceholder","InstagramLink":"InstagramLinkPlaceholder"}
         */

        private String CompanyAddress;
        private String ContactNumbersWithExtn;
        private String PrimaryContactNo;
        private String PrimaryEmailAddress;
        private KuwaitAlKhairSocialMediaHandlesBean KuwaitAlKhairSocialMediaHandles;

        public String getCompanyAddress() {
            return CompanyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            CompanyAddress = companyAddress;
        }

        public String getContactNumbersWithExtn() {
            return ContactNumbersWithExtn;
        }

        public void setContactNumbersWithExtn(String contactNumbersWithExtn) {
            ContactNumbersWithExtn = contactNumbersWithExtn;
        }

        public String getPrimaryContactNo() {
            return PrimaryContactNo;
        }

        public void setPrimaryContactNo(String primaryContactNo) {
            PrimaryContactNo = primaryContactNo;
        }

        public String getPrimaryEmailAddress() {
            return PrimaryEmailAddress;
        }

        public void setPrimaryEmailAddress(String primaryEmailAddress) {
            PrimaryEmailAddress = primaryEmailAddress;
        }

        public KuwaitAlKhairSocialMediaHandlesBean getKuwaitAlKhairSocialMediaHandles() {
            return KuwaitAlKhairSocialMediaHandles;
        }

        public void setKuwaitAlKhairSocialMediaHandles(KuwaitAlKhairSocialMediaHandlesBean kuwaitAlKhairSocialMediaHandles) {
            KuwaitAlKhairSocialMediaHandles = kuwaitAlKhairSocialMediaHandles;
        }

        public static class KuwaitAlKhairSocialMediaHandlesBean {
            /**
             * FacebookLink : FacebookLinkPlaceholder
             * TwitterLink : TwitterLinkPlaceholder
             * YouTubeLink : YouTubeLinkPlaceholder
             * InstagramLink : InstagramLinkPlaceholder
             */

            private String FacebookLink;
            private String TwitterLink;
            private String YouTubeLink;
            private String InstagramLink;

            public String getFacebookLink() {
                return FacebookLink;
            }

            public void setFacebookLink(String facebookLink) {
                FacebookLink = facebookLink;
            }

            public String getTwitterLink() {
                return TwitterLink;
            }

            public void setTwitterLink(String twitterLink) {
                TwitterLink = twitterLink;
            }

            public String getYouTubeLink() {
                return YouTubeLink;
            }

            public void setYouTubeLink(String youTubeLink) {
                YouTubeLink = youTubeLink;
            }

            public String getInstagramLink() {
                return InstagramLink;
            }

            public void setInstagramLink(String instagramLink) {
                InstagramLink = instagramLink;
            }
        }
    }
}

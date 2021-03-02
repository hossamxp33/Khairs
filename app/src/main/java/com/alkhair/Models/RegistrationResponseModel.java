package com.alkhair.Models;


public  class RegistrationResponseModel {


    /**
     * success : true
     * result : {"RegistrationId":32,"FullName":"MobUser Mufasir","Mobile":"61789654","Email":"test@yahoo.com","Password":null,"MessageToUser":null,"FirstName":null,"LastName":null,"ResponseMessage":"Donor registered"}
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
         * RegistrationId : 32
         * FullName : MobUser Mufasir
         * Mobile : 61789654
         * Email : test@yahoo.com
         * Password : null
         * MessageToUser : null
         * FirstName : null
         * LastName : null
         * ResponseMessage : Donor registered
         */

        private Integer RegistrationId;
        private String FullName;
        private String Mobile;
        private String Email;
        private Object Password;
        private String MessageToUser;
        private Object FirstName;
        private Object LastName;
        private String ResponseMessage;

        public Integer getRegistrationId() {
            return RegistrationId;
        }

        public void setRegistrationId(Integer registrationId) {
            RegistrationId = registrationId;
        }

        public String getFullName() {
            return FullName;
        }

        public void setFullName(String fullName) {
            FullName = fullName;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public Object getPassword() {
            return Password;
        }

        public void setPassword(Object password) {
            Password = password;
        }

        public String getMessageToUser() {
            return MessageToUser;
        }

        public void setMessageToUser(String messageToUser) {
            MessageToUser = messageToUser;
        }

        public Object getFirstName() {
            return FirstName;
        }

        public void setFirstName(Object firstName) {
            FirstName = firstName;
        }

        public Object getLastName() {
            return LastName;
        }

        public void setLastName(Object lastName) {
            LastName = lastName;
        }

        public String getResponseMessage() {
            return ResponseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            ResponseMessage = responseMessage;
        }
    }
}

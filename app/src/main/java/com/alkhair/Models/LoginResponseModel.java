package com.alkhair.Models;


public class LoginResponseModel {

    /**
     * success : true
     * result : {"RegistrationId":18,"FullName":"pavan Venkat","Mobile":"61789654","Email":"s.sankari@e.net.kw","Password":null,"MessageToUser":"Active User","FirstName":null,"LastName":null,"ResponseMessage":null}
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
         * RegistrationId : 18
         * FullName : pavan Venkat
         * Mobile : 61789654
         * Email : s.sankari@e.net.kw
         * Password : null
         * MessageToUser : Active User
         * FirstName : null
         * LastName : null
         * ResponseMessage : null
         */

        private Integer RegistrationId;
        private String FullName;
        private String Mobile;
        private String Email;
        private Object Password;
        private String MessageToUser;
        private Object FirstName;
        private Object LastName;
        private Object ResponseMessage;

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

        public Object getResponseMessage() {
            return ResponseMessage;
        }

        public void setResponseMessage(Object responseMessage) {
            ResponseMessage = responseMessage;
        }
    }
}

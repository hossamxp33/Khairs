package com.alkhair.Models;

public class ResetPasswordResponseModel {


    /**
     * success : true
     * result : Password reset
     */

    private String success;
    private String result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

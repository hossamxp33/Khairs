package com.alkhair.Models;

import java.util.List;


public  class PaymentWaysResponse {


    /**
     * success : true
     * result : [{"PaymentTypeId":1,"MinAmount":1,"PaymentTypeName":"KNET","PaymentOptionImagePath":"http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/1.jpg"},{"PaymentTypeId":2,"MinAmount":1,"PaymentTypeName":"Credit Card","PaymentOptionImagePath":"http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/2.jpg"}]
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
         * PaymentTypeId : 1
         * MinAmount : 1.0
         * PaymentTypeName : KNET
         * PaymentOptionImagePath : http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/http://test.e.net.kw/kuwaitcharityadmin/images/paymentoptions/1.jpg
         */
        private Boolean Checked = false;
        private Integer PaymentTypeId;
        private Double MinAmount;
        private String PaymentTypeName;
        private String PaymentOptionImagePath;

        public Integer getPaymentTypeId() {
            return PaymentTypeId;
        }

        public void setPaymentTypeId(Integer paymentTypeId) {
            PaymentTypeId = paymentTypeId;
        }

        public Double getMinAmount() {
            return MinAmount;
        }

        public void setMinAmount(Double minAmount) {
            MinAmount = minAmount;
        }

        public Boolean getChecked() {
            return Checked;
        }

        public void setChecked(Boolean checked) {
            Checked = checked;
        }

        public String getPaymentTypeName() {
            return PaymentTypeName;
        }

        public void setPaymentTypeName(String paymentTypeName) {
            PaymentTypeName = paymentTypeName;
        }

        public String getPaymentOptionImagePath() {
            return PaymentOptionImagePath;
        }

        public void setPaymentOptionImagePath(String paymentOptionImagePath) {
            PaymentOptionImagePath = paymentOptionImagePath;
        }
    }
}

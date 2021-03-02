package com.alkhair.Models;

import java.util.List;

public class DonationsHistoryRespopnse {


    /**
     * success : true
     * result : [{"PaymentTransactionId":8493903,"PaymentDateDisplay":"1/13/2021","DonationAmount":5,"ProjectName":" KRCS-Test Project 1"},{"PaymentTransactionId":8493806,"PaymentDateDisplay":"1/10/2021","DonationAmount":1,"ProjectName":"Test Project 1"}]
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
         * PaymentTransactionId : 8493903
         * PaymentDateDisplay : 1/13/2021
         * DonationAmount : 5.0
         * ProjectName :  KRCS-Test Project 1
         */

        private String PaymentTransactionId;
        private String PaymentDateDisplay;
        private Double DonationAmount;
        private String ProjectName;

        public String getPaymentTransactionId() {
            return PaymentTransactionId;
        }

        public void setPaymentTransactionId(String paymentTransactionId) {
            PaymentTransactionId = paymentTransactionId;
        }

        public String getPaymentDateDisplay() {
            return PaymentDateDisplay;
        }

        public void setPaymentDateDisplay(String paymentDateDisplay) {
            PaymentDateDisplay = paymentDateDisplay;
        }

        public Double getDonationAmount() {
            return DonationAmount;
        }

        public void setDonationAmount(Double donationAmount) {
            DonationAmount = donationAmount;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String projectName) {
            ProjectName = projectName;
        }
    }
}

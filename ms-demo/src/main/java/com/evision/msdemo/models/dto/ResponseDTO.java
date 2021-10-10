package com.evision.msdemo.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseDTO {
    private String responseCode;
    private String responseDesc;
    @JsonIgnore
    private String VerificationErrors;

    private String validationErrors;

    @JsonIgnore
    private int validTransactions;
    @JsonIgnore
    private int invalidTransactions;

    private int withdrawTransactions;
    @JsonIgnore
    public String getVerificationErrors() {
        return VerificationErrors;
    }

    public void setVerificationErrors(String verificationErrors) {
        VerificationErrors = verificationErrors;
    }
    @JsonIgnore
    public String getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(String validationErrors) {
        this.validationErrors = validationErrors;
    }

    public int getValidTransactions() {
        return validTransactions;
    }

    public void setValidTransactions(int validTransactions) {
        this.validTransactions = validTransactions;
    }

    public int getInvalidTransactions() {
        return invalidTransactions;
    }

    public void setInvalidTransactions(int invalidTransactions) {
        this.invalidTransactions = invalidTransactions;
    }

    public int getWithdrawTransactions() {
        return withdrawTransactions;
    }

    public void setWithdrawTransactions(int withdrawTransactions) {
        this.withdrawTransactions = withdrawTransactions;
    }

    public int getDepositTransactions() {
        return depositTransactions;
    }

    public void setDepositTransactions(int depositTransactions) {
        this.depositTransactions = depositTransactions;
    }

    private int depositTransactions;

    public ResponseDTO() {
    }

    public ResponseDTO(String responseCode, String responseDesc) {
        this.responseCode = responseCode;
        this.responseDesc = responseDesc;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }
    @JsonIgnore
    public boolean isVerified(){
        return  getVerificationErrors().isEmpty();
    }
    @JsonIgnore
    public boolean isValid(){
        return  getValidationErrors().isEmpty();
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "responseCode='" + responseCode + '\'' +
                ", responseDesc='" + responseDesc + '\'' +
                '}';
    }
}

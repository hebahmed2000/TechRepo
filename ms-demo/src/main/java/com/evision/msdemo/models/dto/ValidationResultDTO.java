package com.evision.msdemo.models.dto;

import java.util.List;

public class ValidationResultDTO {

    private String validationErrors;

    private int validTransactions;

    private int invalidTransactions;

    List<Transaction> transactionList;

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

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}

package com.evision.msdemo.models.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.util.Date;

public class Transaction {


    @CsvBindByPosition(position = 0,format = "0000-0000-0000-0000")
    private String cardNo;

    @CsvBindByPosition(position = 1,required = true)
    private String accountNo;

    @CsvBindByPosition(position = 2,required = true)
    private String transactionType;

    @CsvBindByPosition(position = 3,required = true)
    private Double amount;

    @CsvBindByPosition(position = 4,required = true)
    @CsvDate(value = "dd-MM-yyyy")
    private Date transactionDate;

    public Transaction() {
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Transaction(String transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction(Double amount) {
        this.amount = amount;
    }


    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}

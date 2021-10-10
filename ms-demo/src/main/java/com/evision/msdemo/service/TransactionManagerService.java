package com.evision.msdemo.service;

import com.evision.msdemo.models.dto.ResponseDTO;
import com.evision.msdemo.models.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TransactionManagerService {

    public final String WITHDRAW_TRANSACTION_TYPE = "withdraw";
    public final String DEPOSIT_TRANSACTION_TYPE = "deposit";

    public void processTransactions(){

    }

     public void calculateTransactionsByType(ResponseDTO responseDTO, List<Transaction> transactionsList) {
        responseDTO.setWithdrawTransactions(countWithdrawTransactions(transactionsList));
        responseDTO.setDepositTransactions(countDepositTransactions(transactionsList));
    }

    protected int countWithdrawTransactions( List<Transaction> transactionList ) {
        return ((List)transactionList.stream().filter(isWithdrawTransaction()).collect(Collectors.<Transaction>toList())).size();
    }

    protected int countDepositTransactions( List<Transaction> transactionList ) {
        Predicate predicate1 = isDepositTransaction();
        return ((List)transactionList.stream().filter(isDepositTransaction()).collect(Collectors.<Transaction>toList())).size();

    }

    private Predicate<Transaction> isDepositTransaction() {
        return p -> DEPOSIT_TRANSACTION_TYPE.equals(p.getTransactionType());
    }
    private Predicate<Transaction> isWithdrawTransaction() {
        return p -> WITHDRAW_TRANSACTION_TYPE.equals(p.getTransactionType());
    }
}

package com.modernlights.service;

import com.modernlights.model.Order;
import com.modernlights.model.Seller;
import com.modernlights.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}

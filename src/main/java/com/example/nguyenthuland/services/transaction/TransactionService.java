package com.example.nguyenthuland.services.transaction;

import com.example.nguyenthuland.models.transaction.TransactionDto;

public interface TransactionService {
  Object create(TransactionDto dto);
  Object updateStatus(String content);
  Object getTransactions(String userName);
  Object getTransactionDetail(String uuid);
  Object get();
}

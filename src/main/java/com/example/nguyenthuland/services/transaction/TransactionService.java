package com.example.nguyenthuland.services.transaction;

import com.example.nguyenthuland.models.transaction.TransactionDto;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public interface TransactionService {
  Object create(TransactionDto dto);
  Object updateStatus(String content);
  Object getTransactions(HttpServletRequest request, String userName, LocalDate fromDate, LocalDate toDate, Integer pageNo, Integer pageSize);
  Object getTransactionDetail(String uuid);
  Object get(String uuid, String bank);
}

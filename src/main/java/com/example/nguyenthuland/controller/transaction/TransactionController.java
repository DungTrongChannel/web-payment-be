package com.example.nguyenthuland.controller.transaction;

import com.example.nguyenthuland.models.transaction.TransactionDto;
import com.example.nguyenthuland.services.transaction.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService service;

  @PostMapping()
  public Object createTransaction(@RequestBody TransactionDto dto) {
    return service.create(dto);
  }

  @PutMapping()
  public Object updateStatusTransaction(@RequestParam String uuid) {
    return service.updateStatus(uuid);
  }

  @GetMapping()
  public Object getTransactions(
      @NonNull HttpServletRequest request,
      @RequestParam("userName") String userName,
      @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
      @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
      @RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize) {
    return service.getTransactions(request, userName, fromDate, toDate, pageNo, pageSize);
  }

  @GetMapping("/detail")
  public Object getTransactionDetail(@RequestParam("uuid") String uuid) {
    return service.getTransactionDetail(uuid);
  }

  @GetMapping("/check-payment")
  public Object get(
      @RequestParam("uuid") String uuid,
      @RequestParam("bank") String bank) {
    return service.get(uuid, bank);
  }
}

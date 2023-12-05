package com.example.nguyenthuland.servicesImpl.TransactionServiceImpl;

import com.example.nguyenthuland.common.Constant;
import com.example.nguyenthuland.domains.transaction.TransactionEntity;
import com.example.nguyenthuland.domains.user.UserEntity;
import com.example.nguyenthuland.models.transaction.TransactionDto;
import com.example.nguyenthuland.repositories.transaction.TransactionRepository;
import com.example.nguyenthuland.repositories.user.UserRepository;
import com.example.nguyenthuland.services.transaction.TransactionService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;
  private final UserRepository userRepository;

  @Override
  public Object create(TransactionDto dto) {
    TransactionEntity entity = new TransactionEntity(dto);
    repository.save(entity);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Override
  public Object updateStatus(String uuid) {
    Optional<TransactionEntity> transaction = repository.findFirstByUuid(uuid);
    if (transaction.isPresent()) {
      transaction.get().setStatus(1);
      transaction.get().setUpdatedDate(LocalDateTime.now());
      repository.save(transaction.get());
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  @Override
  public Object getTransactions(String userName) {
    Optional<UserEntity> user = userRepository.findByUsername(userName);
    List<TransactionEntity> transactions = new ArrayList<>();
    if (user.isPresent()) {
      if (user.get().getRole() == Constant.ROLE_ADMIN) {
        transactions = repository.findAll();
      } else {
        transactions = repository.findByUserNameOrderByCreatedDate(userName);
      }
    }
    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  @Override
  public Object getTransactionDetail(String uuid) {
    Optional<TransactionEntity> transaction = repository.findFirstByUuid(uuid);
    return new ResponseEntity<>(transaction.get(), HttpStatus.ACCEPTED);
  }

  @Override
  public Object get(String uuid, String bank) {
    try {
      // API endpoint URL
      String apiUrl = "";
      if(bank.equals("ACB")) {
        apiUrl = "https://api.web2m.com/historyapiacbv3/Dungkaka94@/17183327/2E189A14-1DCD-9ABB-DC1F-C1F68702CBB3";
      }

      URL url = new URL(apiUrl);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      // Read response
      BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        response.append(line);
      }
      reader.close();
      conn.disconnect();
      // Process API response

      if(response.toString().contains(uuid)) {
        updateStatus(uuid);
        return new ResponseEntity<>(true, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(false, HttpStatus.OK);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}

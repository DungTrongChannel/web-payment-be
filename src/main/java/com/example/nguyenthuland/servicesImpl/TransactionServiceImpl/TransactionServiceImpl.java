package com.example.nguyenthuland.servicesImpl.TransactionServiceImpl;

import com.example.nguyenthuland.common.Constant;
import com.example.nguyenthuland.common.ResponsePage;
import com.example.nguyenthuland.configs.jwt.JwtUtilities;
import com.example.nguyenthuland.domains.transaction.TransactionEntity;
import com.example.nguyenthuland.domains.user.UserEntity;
import com.example.nguyenthuland.models.transaction.TransactionDto;
import com.example.nguyenthuland.repositories.transaction.TransactionRepository;
import com.example.nguyenthuland.repositories.user.UserRepository;
import com.example.nguyenthuland.services.transaction.TransactionService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository repository;
  private final UserRepository userRepository;
  private final JwtUtilities jwtUtilities;

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
  public Object getTransactions(HttpServletRequest request, String userName, LocalDate fromDate, LocalDate toDate, Integer pageNo, Integer pageSize) {
    LocalDateTime fromDateTime = LocalDateTime.of(fromDate, LocalTime.MIN);
    LocalDateTime toDateTime = LocalDateTime.of(toDate, LocalTime.MAX);;
    Pageable paging = PageRequest.of(pageNo, pageSize);

    // get current user
    String token = jwtUtilities.getToken(request);
    Claims claims = jwtUtilities.extractAllClaims(token);

    Page<TransactionEntity> pageData;
    // role Admin -> can search all user
    if(claims.get("role").toString().contains(String.valueOf(Constant.ROLE_ADMIN))) {
      pageData = repository.findByUserNameContainsIgnoreCaseAndCreatedDateBetweenOrderByCreatedDateDesc(userName, fromDateTime, toDateTime, paging);
    } else {
      // role Employee ->
      pageData = repository.findByUserNameContainsIgnoreCaseAndCreatedDateBetweenOrderByCreatedDateDesc(claims.get("sub").toString(), fromDateTime, toDateTime, paging);
    }
    ResponsePage response = ResponsePage.builder()
        .pageNo(pageNo)
        .totalPages(pageData.getTotalPages())
        .totalElements(pageData.getTotalElements())
        .content(pageData.getContent()).build();
    return new ResponseEntity<>(response, HttpStatus.OK);
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

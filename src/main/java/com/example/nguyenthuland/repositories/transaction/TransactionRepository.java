package com.example.nguyenthuland.repositories.transaction;

import com.example.nguyenthuland.domains.transaction.TransactionEntity;
import com.example.nguyenthuland.domains.user.UserEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, String>,
    JpaRepository<TransactionEntity, String> {
  Optional<TransactionEntity> findFirstByUuid(String uuid);
  List<TransactionEntity> findByUserNameOrderByCreatedDate(String userName);

  Page<TransactionEntity> findByUserNameContainsIgnoreCaseAndCreatedDateBetweenOrderByCreatedDateDesc(String userName, LocalDateTime fromDate, LocalDateTime toDate, Pageable paging);
  List<TransactionEntity> findByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);
}

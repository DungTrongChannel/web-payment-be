package com.example.nguyenthuland.domains.transaction;

import com.example.nguyenthuland.common.Constant;
import com.example.nguyenthuland.models.transaction.TransactionDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "transaction")
public class TransactionEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "bank_name")
  private String bankName;

  @Column(name = "bank_number")
  private String bankNumber;

  @Column(name = "amount")
  private String amount;

  @Column(name = "content")
  private String content;

  @Column(name = "status")
  private int status;

  @Column(name = "uuid")
  private String uuid;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "updated_date")
  private LocalDateTime updatedDate;

  public TransactionEntity(TransactionDto dto) {
    this.userName = dto.getUserName();
    this.bankName = dto.getAcqName();
    this.bankNumber = dto.getAccountNo();
    this.amount = dto.getAmount();
    this.content = dto.getContent();
    this.status = Constant.STATUS_DOING;
    this.uuid = dto.getUuid();
    this.createdDate = LocalDateTime.now();
    this.updatedDate = LocalDateTime.now();
  }
}

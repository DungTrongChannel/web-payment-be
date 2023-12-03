package com.example.nguyenthuland.models.transaction;

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
public class TransactionDto {

  private Integer id;

  private String userName;

  private String acqName; //bank name

  private String accountNo; //bankNumber

  private String amount;

  private String content;

  private int status;

  private String uuid;
}

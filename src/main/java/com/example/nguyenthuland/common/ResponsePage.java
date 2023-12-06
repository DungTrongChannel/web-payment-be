package com.example.nguyenthuland.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponsePage {
  private Integer pageNo;
  private Long totalElements;
  private Integer totalPages;
  private Object content;
}

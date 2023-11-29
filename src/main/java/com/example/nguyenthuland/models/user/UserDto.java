package com.example.nguyenthuland.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
  private String username;
  private String password;
  private int role;
}

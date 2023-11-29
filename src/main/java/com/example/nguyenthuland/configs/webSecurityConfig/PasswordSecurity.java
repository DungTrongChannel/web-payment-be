package com.example.nguyenthuland.configs.webSecurityConfig;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordSecurity {

  public static String encryptPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(4));
  }

  public static boolean checkPassword(String password, String passwordSave) {
    return BCrypt.checkpw(password, passwordSave);
  }
}

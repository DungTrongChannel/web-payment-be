package com.example.nguyenthuland.servicesImpl.userServiceImpl;

import com.example.nguyenthuland.common.Constant;
import com.example.nguyenthuland.common.Message;
import com.example.nguyenthuland.common.ResponseError;
import com.example.nguyenthuland.configs.jwt.JwtUtilities;
import com.example.nguyenthuland.configs.webSecurityConfig.PasswordSecurity;
import com.example.nguyenthuland.domains.user.UserEntity;
import com.example.nguyenthuland.models.user.UserDto;
import com.example.nguyenthuland.repositories.user.UserRepository;
import com.example.nguyenthuland.services.user.UserService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtUtilities jwtUtilities;

  /*
   * Register User
   * */
  @Override
  public Object create(UserDto dto) {
    UserEntity userEntity = new UserEntity(dto);
    if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
      return new ResponseEntity<>(
          ResponseError.responseError("SAME_USERNAME", Message.SAME_USERNAME),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // set role employee
    userEntity.setRole(Constant.ROLE_EMPLOYEE); // 0: admin // 1: employee
    // encrypt password
    userEntity.setPassword(PasswordSecurity.encryptPassword(userEntity.getPassword()));
    // create user
    userRepository.save(userEntity);
    // JWT generate token
    String token = jwtUtilities.generateToken(dto.getUsername(),
        Collections.singletonList(String.valueOf(dto.getRole())));
    return new ResponseEntity<>(responseToken(token), HttpStatus.CREATED);
  }

  /*
   * Login
   * */
  @Override
  public Object login(UserDto dto) {
    Optional<UserEntity> userEntity = userRepository.findByUsername(dto.getUsername());
    if (userEntity.isPresent() && PasswordSecurity.checkPassword(dto.getPassword(),
        userEntity.get().getPassword())) {
      // JWT generate token
      String token = jwtUtilities.generateToken(userEntity.get().getUsername(),
          Collections.singletonList(String.valueOf(userEntity.get().getRole())));
      return new ResponseEntity<>(responseToken(token), HttpStatus.OK);
    }
    return new ResponseEntity<>(
        ResponseError.responseError("LOGIN_ERROR", Message.LOGIN_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /*
   * Response type and token
   * */
  public Map<String, String> responseToken(String token) {
    Map<String, String> response = new HashMap<>();
    response.put("tokenType", "Bearer");
    response.put("token", token);
    return response;
  }
}

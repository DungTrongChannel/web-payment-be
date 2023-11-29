package com.example.nguyenthuland.controller.userController;

import com.example.nguyenthuland.models.user.UserDto;
import com.example.nguyenthuland.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /*
   * REGISTER USER
   * */
  @PostMapping("/register")
  public Object createUser(@RequestBody UserDto dto) {
    return userService.create(dto);
  }


  /*
   * LOGIN
   * */
  @PostMapping("/login")
  public Object getUserEntity(@RequestBody UserDto dto) {
    return userService.login(dto);
  }
}

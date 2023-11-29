package com.example.nguyenthuland.services.user;

import com.example.nguyenthuland.models.user.UserDto;

public interface UserService {

  Object create(UserDto dto);
  Object login(UserDto dto);
}

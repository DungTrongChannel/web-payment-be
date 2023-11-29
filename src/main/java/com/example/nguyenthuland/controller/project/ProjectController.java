package com.example.nguyenthuland.controller.project;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

  @PostMapping("/test-authen")
  public Object getUserEntity() {
    return "Hello";
  }
}

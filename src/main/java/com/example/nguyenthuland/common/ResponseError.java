package com.example.nguyenthuland.common;

import java.util.HashMap;
import java.util.Map;

public class ResponseError {
  public static Map<String, String> responseError(String errorCode, String errorMsg) {
    Map<String, String> response = new HashMap<>();
    response.put("errorCode", errorCode);
    response.put("errorMsg", errorMsg);
    return response;
  }
}

package com.niit.authentication.services;

import com.niit.authentication.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {

  Map<String,String> generateToken(User user);
}

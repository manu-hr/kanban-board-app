package com.niit.authentication.services;

import com.niit.authentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator  {

  @Override
  public Map<String, String> generateToken(User user) {

    String jwtToken = null;
    Map<String, Object> userData = new HashMap<>();
    System.out.println("User"+ user);

    userData.put("user_email",user.getEmail());
    userData.put("user_role",user.getUserRole());
    userData.put("userId",user.getUserId());

    jwtToken = Jwts.builder()
              .setClaims(userData)
              .setIssuedAt(new Date())
              .signWith(SignatureAlgorithm.HS256,"secretkey").compact();

    Map<String,String> map = new HashMap<>();
    map.put("token",jwtToken);
    map.put("message", "User Successfully logged in");
    return map;
  }
}


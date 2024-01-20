package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.auth.JwtUtil;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.io.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtUtil jwtUtil;
  public String login(LoginRequest loginRequest) {
    var claim = new JwtClaim()
        .setUserId(1);
    return jwtUtil.generateToken(claim);
  }
}

package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.constants.AuthRoute;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.io.LoginRequest;
import com.shhrrtnvr.smartaquaculture.io.LoginResponse;
import com.shhrrtnvr.smartaquaculture.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerRoute.AUTH_ROUTE)
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping(AuthRoute.LOGIN)
  public ResponseEntity<LoginResponse> login(
      @RequestBody LoginRequest request
  ) {
    var token = authService.login(request);
    var response = new LoginResponse()
        .setAccessToken(token);
    return ResponseEntity.ok(response);
  }
}

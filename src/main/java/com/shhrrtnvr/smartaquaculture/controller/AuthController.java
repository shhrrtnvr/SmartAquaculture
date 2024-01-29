package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.Role;
import com.shhrrtnvr.smartaquaculture.constants.AuthRoute;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.io.LoginRequest;
import com.shhrrtnvr.smartaquaculture.io.LoginResponse;
import com.shhrrtnvr.smartaquaculture.io.UserInfo;
import com.shhrrtnvr.smartaquaculture.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.AUTH_ROUTE)
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

  @PostMapping(AuthRoute.SIGN_UP)
  public ResponseEntity<Boolean> signUp(
      @RequestBody UserInfo request
  ) {
    request.setRole(Role.USER);
    var result = authService.addUser(request);
    return ResponseEntity.ok(result);
  }

  @PostMapping(AuthRoute.LOGIN)
  public ResponseEntity<LoginResponse> login(
      @RequestBody LoginRequest request
  ) throws AuthException {
    var token = authService.login(request);
    var response = new LoginResponse()
        .setAccessToken(token);
    return ResponseEntity.ok(response);
  }

  @PutMapping(AuthRoute.UPDATE_INFO)
  public ResponseEntity<Boolean> updateInfo(
      @RequestBody UserInfo request,
      @RequestingUser Long userId
  ) throws AuthException {
    var result = authService.resetPassword(userId, request);
    return ResponseEntity.ok(result);
  }
}

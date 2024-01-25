package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.constants.UserRoute;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.factory.mapper.UserMapper;
import com.shhrrtnvr.smartaquaculture.io.UserResponse;
import com.shhrrtnvr.smartaquaculture.model.User;
import com.shhrrtnvr.smartaquaculture.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.USER_ROUTE)
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;

  @GetMapping(UserRoute.INFO)
  public ResponseEntity<UserResponse> getInfo(@RequestingUser JwtClaim claim) {
    var user = userRepository.findById(claim.getUserId());
    return ResponseEntity.ok(
        UserMapper.toUserResponse(user.orElse(new User()))
    );
  }
}

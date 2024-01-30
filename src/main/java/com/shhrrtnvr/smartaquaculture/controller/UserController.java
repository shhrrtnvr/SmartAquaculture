package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.bo.Role;
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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.USER_ROUTE)
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;

  @GetMapping(UserRoute.LIST)
  public ResponseEntity<List<UserResponse>> list(@RequestingUser JwtClaim claim) {
    if (!claim.getRole().equals(Role.ADMIN))
      return ResponseEntity.badRequest().build();

    Iterable<User> userItem = userRepository.findAll();
    List<UserResponse> users = new ArrayList<>();
    userItem.forEach(user -> {
      users.add(UserMapper.toUserResponseWithoutDevices(user));
    });
    return ResponseEntity.ok(users);
  }

  @GetMapping(UserRoute.INFO)
  public ResponseEntity<UserResponse> getInfo(@RequestingUser JwtClaim claim) {
    var user = userRepository.findById(claim.getUserId());
    return ResponseEntity.ok(
        UserMapper.toUserResponse(user.orElse(new User()))
    );
  }
}

package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.bo.Role;
import com.shhrrtnvr.smartaquaculture.constants.UserRoute;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.factory.mapper.UserMapper;
import com.shhrrtnvr.smartaquaculture.io.SignUpRequest;
import com.shhrrtnvr.smartaquaculture.io.UserResponse;
import com.shhrrtnvr.smartaquaculture.model.User;
import com.shhrrtnvr.smartaquaculture.repository.UserRepository;
import com.shhrrtnvr.smartaquaculture.service.AuthService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ControllerRoute.USER_ROUTE)
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;
  private final AuthService authService;

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

  @GetMapping(UserRoute.INFO_ADMIN)
  public ResponseEntity<UserResponse> getInfoByAdmin(
      @PathVariable Long id,
      @RequestingUser JwtClaim claim
  ) throws AuthException {
    if (claim.getRole() != Role.ADMIN) throw new AuthException("Not Allowed");

    var user = userRepository.findById(id);
    return ResponseEntity.ok(
        UserMapper.toUserResponse(user.orElse(new User()))
    );
  }

  @PostMapping(UserRoute.ADD)
  public ResponseEntity<Boolean> add(
      @RequestBody SignUpRequest request,
      @RequestingUser JwtClaim claim
  ) throws AuthException {
    if (claim.getRole() != Role.ADMIN) throw new AuthException("Not Allowed");

    var result = authService.addUser(request);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping(UserRoute.DELETE)
  public ResponseEntity<Boolean> delete(
      @PathVariable Long id,
      @RequestingUser JwtClaim claim
  ) throws AuthException {
    if (claim.getRole() != Role.ADMIN) throw new AuthException("Not Allowed");

    userRepository.deleteById(id);
    return ResponseEntity.ok(true);
  }

}

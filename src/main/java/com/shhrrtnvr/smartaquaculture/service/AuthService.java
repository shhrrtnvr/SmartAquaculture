package com.shhrrtnvr.smartaquaculture.service;

import com.shhrrtnvr.smartaquaculture.auth.JwtUtil;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.io.LoginRequest;
import com.shhrrtnvr.smartaquaculture.io.UserInfo;
import com.shhrrtnvr.smartaquaculture.model.User;
import com.shhrrtnvr.smartaquaculture.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final JwtUtil jwtUtil;
  private final Argon2PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  public boolean addUser(UserInfo request) throws PersistenceException {
    var user = userRepository.findByUsername(request.getUsername());
    user.ifPresent(u -> {
      throw new PersistenceException("User already exists");
    });

    var hashedPassword = passwordEncoder.encode(request.getPassword());
    var newUser = new User()
        .setUsername(request.getUsername())
        .setFirstName(request.getFirstName())
        .setLastName(request.getLastName())
        .setPassword(hashedPassword)
        .setRole(request.getRole());

    userRepository.save(newUser);
    return true;
  }
  public String login(LoginRequest loginRequest) throws PersistenceException, AuthException {
    var user = userRepository.findByUsername(loginRequest.getUsername())
        .orElseThrow(() -> new PersistenceException("User not found"));

    if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new AuthException("Password is incorrect");
    }

    var claim = new JwtClaim()
        .setUserId(user.getId())
        .setUsername(user.getUsername())
        .setRole(user.getRole());

    return jwtUtil.generateToken(claim);
  }

  public Boolean userUpdate(Long userId, UserInfo info) throws AuthException {
    var user = userRepository.findById(userId).orElseThrow(
        () -> new AuthException("User not found")
    );
    if (user.getRole() != info.getRole()) {
      throw new AuthException("User role is incorrect");
    }

    if (info.getUsername() != null) {
      user.setUsername(info.getUsername());
    }
    if (info.getFirstName() != null) {
      user.setFirstName(info.getFirstName());
    }
    if (info.getLastName() != null) {
      user.setLastName(info.getLastName());
    }
    if (info.getPassword() != null) {
      var hashedPassword = passwordEncoder.encode(info.getPassword());
      user.setPassword(hashedPassword);
    }
    userRepository.save(user);
    return true;
  }
}

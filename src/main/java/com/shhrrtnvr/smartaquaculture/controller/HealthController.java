package com.shhrrtnvr.smartaquaculture.controller;

import com.shhrrtnvr.smartaquaculture.auth.RequestingUser;
import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.constants.HealthRoute;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
  @GetMapping(HealthRoute.HEALTH)
  public String health() {
    return "OK - Smart Aquaculture";
  }

  @GetMapping(HealthRoute.WHOAMI)
  public ResponseEntity<Long> whoami(@RequestingUser JwtClaim jwtClaim) {
    return ResponseEntity.ok(jwtClaim.getUserId());
  }
}

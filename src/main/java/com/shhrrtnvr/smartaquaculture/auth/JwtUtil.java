package com.shhrrtnvr.smartaquaculture.auth;

import com.shhrrtnvr.smartaquaculture.bo.JwtClaim;
import com.shhrrtnvr.smartaquaculture.config.JwtConfig;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
  private final JwtConfig jwtConfig;
  private final SecretKey secretKey;
  private final JwtParser jwtParser;
  public String generateToken(JwtClaim jwtClaim) {
    var expiration = Instant.now()
        .plusMillis(jwtConfig.getExpiration() * 3600L * 1000L);
    return Jwts.builder()
        .claim("userId", jwtClaim.getUserId())
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .setExpiration(Date.from(expiration))
        .compact();
  }

  public JwtClaim parseToken(String token) {
    var bodyMap = jwtParser
        .parseClaimsJws(token)
        .getBody();

    return new JwtClaim()
        .setUserId(bodyMap.get("userId", Integer.class));
  }
}

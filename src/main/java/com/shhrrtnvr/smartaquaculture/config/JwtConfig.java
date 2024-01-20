package com.shhrrtnvr.smartaquaculture.config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Configuration
@Getter
public class JwtConfig {
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.expiration}")
  private Long expiration;

  @Bean
  public SecretKey getSecret() {
    return new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
  }

  @Bean
  public JwtParser getJwtParser() {
    return Jwts.parserBuilder()
        .setSigningKey(getSecret())
        .build();
  }
}

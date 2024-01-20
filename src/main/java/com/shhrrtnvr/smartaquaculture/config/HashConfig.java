package com.shhrrtnvr.smartaquaculture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Configuration
public class HashConfig {

  @Bean
  public Argon2PasswordEncoder argon2PasswordEncoder() {
    return new Argon2PasswordEncoder(16, 32, 10, 60000, 10);
  }
}

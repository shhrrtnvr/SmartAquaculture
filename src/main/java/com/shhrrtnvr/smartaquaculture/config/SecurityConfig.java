package com.shhrrtnvr.smartaquaculture.config;

import com.shhrrtnvr.smartaquaculture.auth.SecretKeyFilter;
import com.shhrrtnvr.smartaquaculture.auth.TokenAuthFilter;
import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import com.shhrrtnvr.smartaquaculture.constants.HealthRoute;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
  private final TokenAuthFilter tokenAuthFilter;
  private final SecretKeyFilter secretKeyFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .httpBasic().disable()
        .csrf().disable()
        .cors().and()
        .authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
        .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(secretKeyFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.ignoring()
        .requestMatchers(
            request -> request.getServletPath().startsWith(HealthRoute.HEALTH),
            request -> request.getServletPath().startsWith(ControllerRoute.AUTH_ROUTE)
        );
  }

}


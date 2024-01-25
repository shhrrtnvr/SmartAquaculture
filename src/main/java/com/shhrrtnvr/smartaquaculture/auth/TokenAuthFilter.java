package com.shhrrtnvr.smartaquaculture.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@AllArgsConstructor
public class TokenAuthFilter extends OncePerRequestFilter {
  private static final String AUTH_HEADER = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    var authHeader = request.getHeader(AUTH_HEADER);
    if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
      authHeader = authHeader.replace(TOKEN_PREFIX, "");
      var claim = jwtUtil.parseToken(authHeader);
      if (claim != null)
        setAuthentication(request, claim);
    }
    filterChain.doFilter(request, response);
  }


  protected void setAuthentication(HttpServletRequest request, Object claim) {
    var authentication = new UsernamePasswordAuthenticationToken(
        claim, claim, Collections.emptyList()
    );
    authentication.setDetails(new WebAuthenticationDetailsSource()
        .buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

}

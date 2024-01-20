package com.shhrrtnvr.smartaquaculture.auth;

import com.shhrrtnvr.smartaquaculture.constants.ControllerRoute;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class SecretKeyFilter extends OncePerRequestFilter {
  private static final String SECURITY_HEADER = "secret-key";

  @Value("${auth.secret.key}")
  private String secretKey;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (isKeyMatched(request)) {
      var authentication = new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());
      authentication.setDetails(new WebAuthenticationDetailsSource()
          .buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      response.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    var path = request.getRequestURI();
    return !path.startsWith(ControllerRoute.ADMIN_ROUTE);
  }

  private boolean isKeyMatched(HttpServletRequest request) {
    var securityHeader = request.getHeader(SECURITY_HEADER);
    return securityHeader != null && securityHeader.equals(secretKey);
  }
}

package com.example.nguyenthuland.configs.jwt;

import com.example.nguyenthuland.configs.services.CustomerUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtilities jwtUtilities;
  private final CustomerUserDetailsService customerUserDetailsService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    String token = jwtUtilities.getToken(request);

    if (token != null && jwtUtilities.validateToken(token)) {
      String email = jwtUtilities.extractUsername(token);

      UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);
      if (userDetails != null) {
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,
                userDetails.getAuthorities());
        log.info("authenticated user with username :{}", email);
        SecurityContextHolder.getContext().setAuthentication(authentication);

      }
    }
    filterChain.doFilter(request, response);
  }

}

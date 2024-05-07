package com.newlecmineursprj.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoginAuthenticFailure implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("onAuthenticationFailure 호출");
        if (exception instanceof UsernameNotFoundException) {
            log.error("Error UsernameNotFoundException :  {} ", exception);
            response.sendRedirect("/signin?error=" + URLEncoder.encode(exception.getMessage(), StandardCharsets.UTF_8));
        } else {
            log.error("Authentication failed", exception);
            response.sendRedirect("/signin");
        }
    }
}

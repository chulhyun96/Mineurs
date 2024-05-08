package com.newlecmineursprj.config.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("비밀번호 인코더 호출");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        log.info("시큐리티 필터체인 호출");
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/admin/**", "/notices/reg").hasRole("ADMIN")
                        .requestMatchers("/myshop/**").hasRole("MEMBER")
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/signin")
                        .failureHandler(new LoginAuthenticFailure())
                        .permitAll())
                .logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .logoutUrl("/signout")
                        .logoutSuccessUrl("/")
                        .permitAll());
        return http.build();
    }
}

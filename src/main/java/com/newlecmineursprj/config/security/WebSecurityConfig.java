package com.newlecmineursprj.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/member/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/admin/**","/notices/reg").hasRole("ADMIN")
                        .requestMatchers("/myshop/**").hasRole("MEMBER")
                        .anyRequest().permitAll())
                .formLogin((form) -> form
                        .loginPage("/signin")
                        .permitAll())
                .logout((logout) -> logout
                        .invalidateHttpSession(true)
                        .logoutUrl("/signout")
                        .logoutSuccessUrl("/")
                        .permitAll());
        return http.build();
    }

}

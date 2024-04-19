package com.newlecmineursprj.config.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
public class WebUserDetails implements UserDetails {

    @Setter
    @Getter
    private Long id;
    private String username;
    @Setter
    private String password;
    @Setter
    private List<GrantedAuthority> authorities;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String phoneNumber;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String paymentPassword;
    @Setter
    @Getter
    private Date regDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

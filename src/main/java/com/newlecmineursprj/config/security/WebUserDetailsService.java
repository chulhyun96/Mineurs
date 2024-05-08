package com.newlecmineursprj.config.security;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.Role;
import com.newlecmineursprj.repository.MemberRepository;
import com.newlecmineursprj.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebUserDetailsService implements UserDetailsService {

    private final MemberRepository repository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Member> member = repository.findByUsername(username);


        List<Role> roles = roleRepository.findAllByMemberId(member.get().getId());

        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return WebUserDetails.builder()
                .id(member.get().getId())
                .username(member.get().getUsername())
                .password(member.get().getPassword())
                .email(member.get().getEmail())
                .phoneNumber(member.get().getPhoneNumber())
                .paymentPassword(member.get().getPaymentPassword())
                .regDate(member.get().getRegDate())
                .authorities(authorities)
                .build();

    }
}

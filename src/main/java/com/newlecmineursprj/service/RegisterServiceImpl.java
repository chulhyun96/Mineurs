package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService{
    private final RegisterRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void reg(Member member) {
        String passwordEncoding = passwordEncoder.encode(member.getPassword());

        member.setEncodedPassword(passwordEncoding);
        repository.save(member);
    }
}

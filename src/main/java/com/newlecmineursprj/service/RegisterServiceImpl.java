package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService{

    //패스워드 암호화
    //시큐리티설정에 철현이가 만들어 놓은 Encoder사용해서 엔티티 이용함.
    private final RegisterRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void reg(Member member) {
        member.encodePassword(passwordEncoder);
        repository.save(member);
    }
}

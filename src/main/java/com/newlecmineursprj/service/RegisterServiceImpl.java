package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.entity.MemberRole;
import com.newlecmineursprj.repository.MemberRoleRepository;
import com.newlecmineursprj.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRoleRepository memberRoleRepository;

    @Override
    @Transactional
    public int reg(Member member) {
        String passwordEncoding = passwordEncoder.encode(member.getPassword());
        member.setEncodedPassword(passwordEncoding);

        try{
            int savedRowCount = repository.save(member);
            MemberRole memberRole = MemberRole.builder()
                    .roleId(1L)
                    .memberId(member.getId())
                    .build();
            
            memberRoleRepository.save(memberRole);

            return savedRowCount;
        } catch (DuplicateKeyException e) {
            log.error("Error during registration: {}", e.getMessage());
            throw e;
        }
    }
}



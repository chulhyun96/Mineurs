package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;


    @Override
    public Member findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Member> getList(String searchMethod, String searchKeyword) {
        if (searchMethod == null)
            return repository.findAll(null, null, null, null, null, null);
        return switch (searchMethod) {
            case "name" -> findByName(searchMethod.trim());
            case "login-id" -> List.of(findByUsername(searchKeyword.trim()));
            default -> null;
        };
    }

    @Override
    public List<Member> findByName(String name) {
        return repository.findAll(null, name, null, null, null, null);
    }

    @Override
    public Member findByUsername(String username) {
        return repository.findAll(null, null, username, null, null, null).get(0);
    }


    @Override
    public void save(Member member) {
        repository.save(member);
    }

    @Override
    public void update(Member member) {
        repository.update(member);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Long> ids) {
        repository.deleteAll(ids);
    }
}

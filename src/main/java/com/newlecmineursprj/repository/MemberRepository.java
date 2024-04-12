package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {
    List<Member> findAll(Long id,
                         String name,
                         String username,
                         String password,
                         String phoneNumber,
                         String email);
    Member findById(long id);

    void save(Member member);

    void update(Member member);

    void deleteById(long id);

    void deleteAll(List<Long> ids);
}

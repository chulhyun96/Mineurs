package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> getList(String searchMethod, String searchKeyword);
    List<Member> findByName(String name);
    Member findByLoginId(String loginId);
    Member findById(long id);

    void save(Member member);

    void update(Member member);

    void deleteById(long id);

    void deleteAll(List<Long> ids);
}

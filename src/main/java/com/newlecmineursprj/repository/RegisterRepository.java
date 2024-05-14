package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterRepository {
    void save(Member member);
}

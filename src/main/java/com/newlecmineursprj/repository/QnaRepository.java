package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Qna;

@Mapper
public interface QnaRepository {
    List<Qna> findAll();
    Qna findById(long id);
    List<Qna> findAllByMemberId(long memberId);
}

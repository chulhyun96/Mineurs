package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaRepository {
    List<Qna> findAll();

    Qna findById(Long id);

    List<Qna> findAllByMemberId(long memberId);

    List<QnaView> findAll(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o);

    void save(Qna qna);

    void update(Long id);

    int findByPassword(Long id, String password);
}

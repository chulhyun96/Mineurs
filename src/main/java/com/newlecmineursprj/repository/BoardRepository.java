package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    List<QnaView> findAll(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o);

    Qna findById(Long id);
}

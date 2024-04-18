package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.QnaCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaCategoryRepository {
    List<QnaCategory> findAll();
}

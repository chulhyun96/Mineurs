package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.ProductItem;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColorRepository {

    List<Color> findAll(long productId);
    Color findById(Long id);

    List<Color> findByKorNameContaining(String query);
    Long findIdByKorName(String korName);
}

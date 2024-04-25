package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Color;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColorRepository {

    List<Color> findAll(long productId);
}

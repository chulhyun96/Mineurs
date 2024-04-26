package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SizeRepository {

    List<Size> findAll(long productId);
}
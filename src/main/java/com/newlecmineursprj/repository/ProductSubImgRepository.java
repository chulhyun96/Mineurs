package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.ProductSubImg;

@Mapper
public interface ProductSubImgRepository {
    void reg(List<ProductSubImg> dimgs);
    List<ProductSubImg> findAll(long productId);
}

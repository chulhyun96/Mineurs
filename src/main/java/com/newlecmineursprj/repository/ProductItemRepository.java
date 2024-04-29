package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.ProductItem;

@Mapper
public interface ProductItemRepository {
    List<ProductItem> findAll();
    ProductItem findById(Long id);
}

package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.entity.ProductView;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll();

    ProductEntity reg(ProductEntity product);

    ProductView findById(Long id);
}

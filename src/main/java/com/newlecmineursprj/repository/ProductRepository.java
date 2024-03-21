package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductEntity> findAll();

    ProductEntity reg(ProductEntity product);

    ProductEntity findById(Long id);
}

package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getList();

    ProductEntity reg(ProductEntity product);

    ProductEntity getById(Long id);
}

package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.entity.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList();

    void reg(ProductEntity product);

    ProductView getById(Long id);
}

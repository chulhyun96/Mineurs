package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList();

    void reg(Product product);

    ProductView getById(Long id);

    void editById(Product product);
}

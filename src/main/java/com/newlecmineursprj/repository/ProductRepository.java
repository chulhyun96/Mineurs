package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll();

    void reg(Product product);

    ProductView findById(Long id);

    public void updateProductById(Product product);
}

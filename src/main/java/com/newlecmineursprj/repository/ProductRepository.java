package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll(String searchMethod, String searchKeyword, int offset, int size);
    void reg(Product product);
    ProductView findById(Long id);
    void updateProductById(ProductView product);
    void deleteAll(List<Long> deleteId);
    int count(String searchMethod, String searchKeyword);
}

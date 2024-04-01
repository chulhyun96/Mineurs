package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

import java.util.List;

public interface ProductService {
    List<ProductView> getList();
    List<ProductView> getList(String searchMethod, String searchKeyword);
    void reg(Product product);
    ProductView getById(Long id);
    void edit(Product product);
    void deleteAllById(List<Long> deleteId);
}

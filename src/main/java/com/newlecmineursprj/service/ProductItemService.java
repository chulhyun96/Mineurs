package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.ProductItem;

public interface ProductItemService {
    List<ProductItem> getList();
    ProductItem getById(Long id);
}

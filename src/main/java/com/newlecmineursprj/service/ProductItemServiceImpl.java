package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.ProductItem;
import com.newlecmineursprj.repository.ProductItemRepository;

@Service
public class ProductItemServiceImpl implements ProductItemService {
    
    @Autowired
    private ProductItemRepository repository;

    @Override
    public List<ProductItem> getList() {
        return repository.findAll();
    }

    @Override
    public ProductItem getById(Long id) {
        return repository.findById(id);
    }

    

}

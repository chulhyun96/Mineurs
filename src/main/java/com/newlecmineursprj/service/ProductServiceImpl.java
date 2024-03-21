package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductEntity> getList() {
        return repository.findAll();
    }

    @Override
    public ProductEntity reg(ProductEntity product) {
        return repository.reg(product);
    }

    @Override
    public ProductEntity getById(Long id) {
        return repository.findById(id);
    }
}

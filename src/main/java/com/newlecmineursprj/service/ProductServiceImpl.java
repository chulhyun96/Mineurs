package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.ProductEntity;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductView> getList() {
        return repository.findAll();
    }

    @Override
    public void reg(ProductEntity product) {
        repository.reg(product);
    }

    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }

}

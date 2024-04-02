package com.newlecmineursprj.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import com.newlecmineursprj.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    @Override
    public List<ProductView> getList() {
        return repository.findAll(null,null);
    }
    @Override
    public List<ProductView> getList(String searchMethod, String searchKeyword) {
        return repository.findAll(searchMethod,searchKeyword);
    }
    @Override
    public void reg(Product product) {
        repository.reg(product);
    }
    @Override
    public ProductView getById(Long id) {
        return repository.findById(id);
    }
    @Override
    public void edit(Product product) {
        repository.updateProductById(product);
    }

    @Override
    public void deleteAllById(List<Long> deleteId) {
        repository.deleteAll(deleteId);
    }
}

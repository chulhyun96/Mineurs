package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Cart;
import com.newlecmineursprj.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartRepository repository;

    @Override
    public List<Cart> getByMid(Long mid) {
        return repository.findByMid(mid);
    }

    

}

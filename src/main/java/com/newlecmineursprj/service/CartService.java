package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Cart;

public interface CartService {
    List<Cart> getByMid(Long mid);
}

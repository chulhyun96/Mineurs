package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Cart;

public interface CartService {

    Cart getById(Long id);

    List<Cart> getByMid(Long mid);

    void delete(Long deleteId);

    void increase(Long cartId);

    void decrease(Long cartId);
}

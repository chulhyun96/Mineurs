package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Cart;

@Mapper
public interface CartRepository {

    Cart findById(Long id);
    List<Cart> findByMid(Long mid);
    Cart findByForeignKeys(Long memberId,Long productItemId);
    void add(Cart cart);
    void delete(Long deleteId);
    void increase(Long cartId);
    void decrease(Long cartId);
}

package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Cart;

@Mapper
public interface CartRepository {
    List<Cart> findByMid(Long mid);
}

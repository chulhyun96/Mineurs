package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Order;

@Mapper
public interface OrderRepository {

    List<Order> findAll();

}

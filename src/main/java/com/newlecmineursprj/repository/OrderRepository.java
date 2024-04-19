package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.OrderView;

@Mapper
public interface OrderRepository {

    List<OrderView> findAll(String searchMethod, String searchKeyword, int offset, int size);

    int count(String searchMethod, String searchKeyword);
}

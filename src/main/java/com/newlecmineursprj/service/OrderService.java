package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderView;

public interface OrderService {

    List<OrderView> getList(Integer page);

    List<OrderView> getList(Integer page, String searchMethod, String searchKeyword);

    int getCount(String searchMethod, String searchKeyword);
}

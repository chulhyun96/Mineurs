package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.util.CustomPageImpl;

public interface OrderService {

    List<OrderView> getList(Integer page);

    CustomPageImpl<OrderView> getList(Integer page, String searchMethod, String searchKeyword, Long memberId);

    CustomPageImpl<OrderView> getList(Integer pageNumber, Integer pageSize, String sortMethod,
                                      String sortDirection, Integer pageGroupSize, String searchMethod,
                                      String searchKeyword, Long memberId, String calendarStart, String calendarEnd,
                                      String startDate, String endDate);

    CustomPageImpl<OrderView> getList(Integer pageNumber, Integer pageSize, String sortMethod,
                                      String sortDirection, Integer pageGroupSize, String searchMethod,
                                      String searchKeyword, Long memberId);

    int getCount(String searchMethod, String searchKeyword, Long memberId);
    int getCount(String searchMethod, String searchKeyword);
    OrderView getById(Long id);
    void add(Order order);
}

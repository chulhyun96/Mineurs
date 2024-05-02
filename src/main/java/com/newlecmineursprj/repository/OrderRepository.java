package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.entity.OrderView;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

@Mapper
public interface OrderRepository {

    List<OrderView> findAll(@Param("pageRequest") Pageable pageRequest
            , @Param("searchMethod")String searchMethod
            , @Param("searchKeyword")String searchKeyword
            , @Param("memberId")Long memberId
    );

    int getCount(String searchMethod, String searchKeyword, Long memberId);

    OrderView findById(Long id);

    void add(Order order);
}

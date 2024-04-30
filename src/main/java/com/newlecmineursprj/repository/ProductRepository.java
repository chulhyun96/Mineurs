package com.newlecmineursprj.repository;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;

@Mapper
public interface ProductRepository {
    List<ProductView> findAll(@Param("pageRequest") Pageable pageRequest, @Param("searchMethod") String searchMethod,
            @Param("searchKeyword") String searchKeyword, @Param("categoryId") Long categoryId,
            @Param("startDate") String startDate, @Param("endDate") String endDate);

    void reg(Product product);

    Product findById(Long id);

    void updateById(Product product);

    void deleteAll(List<Long> deleteId);

    int getCount(String searchMethod, String searchKeyword, Long categoryId);

    List<ProductListDTO> findByRegDate(LocalDate startDate, LocalDate endDate);

    List<ProductView> findAllByMemberId(@Param("pageRequest") Pageable pageRequest, @Param("memberId") long memberId);

    long getCountByMemberId(long memberId);
}

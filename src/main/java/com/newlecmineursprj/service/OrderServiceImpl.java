package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.util.CustomPageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<OrderView> getList(Integer pageNumber) {
        int pageSize = 10;


        return getList (pageNumber, pageSize, "ordered_datetime", "DESC", 5, null,  null, null).getContent();
    }

    @Override
    public CustomPageImpl<OrderView> getList(Integer pageNumber, Integer pageSize, String sortMethod, String sortDirection, Integer pageGroupSize, String searchMethod, String searchKeyword, Long memberId) {
        Pageable pageRequest = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortMethod));

        List<OrderView> content = repository.findAll(pageRequest, searchMethod, searchKeyword, memberId);

        long count = repository.getCount(searchMethod, searchKeyword, memberId);

        return new CustomPageImpl<OrderView>(content, pageRequest, count, pageGroupSize);
    }

    @Override
    public List<OrderView> getList(Integer pageNumber, String searchMethod, String searchKeyword) {
        int pageSize = 10;


        return getList (pageNumber, pageSize, "ordered_datetime", "DESC", 5, searchMethod,  searchKeyword, null).getContent();
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword, Long memberId) {
        return repository.getCount(searchMethod, searchKeyword, memberId);
    }
    @Override
    public int getCount(String searchMethod, String searchKeyword) {
        return getCount(searchMethod, searchKeyword, null);
    }

    @Override
    public OrderView getById(Long id) {
        return repository.findById(id);
    }

}

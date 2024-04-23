package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderView;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public List<OrderView> getList(Integer page) {
        int size = 10;
        int offset = (page - 1) * size;
        return repository.findAll(null, null, offset, size);
    }

    @Override
    public List<OrderView> getList(Integer page, String searchMethod, String searchKeyword) {
        int size = 10;
        int offset = (page - 1) * size;
        return repository.findAll(searchMethod, searchKeyword, offset, size);
    }

    @Override
    public int getCount(String searchMethod, String searchKeyword) {
        return repository.count(searchMethod, searchKeyword);
    }

    @Override
    public OrderView getById(Long id) {
        return repository.findById(id);
    }

}

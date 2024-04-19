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
    public List<OrderView> getList() {
        return repository.findAll();
    }

}

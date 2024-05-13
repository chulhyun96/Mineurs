package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.repository.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository repository;

    @Override
    public List<Size> getList(long productId) {
        return repository.findAll(productId);
    }

    @Override
    public Size getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long getIdByEngName(String engName) {
        return repository.findIdByEngName(engName);
    }
}

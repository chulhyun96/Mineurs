package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.repository.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository repository;

    @Override
    public List<Color> getList(long productId) {
        return repository.findAll(productId);
    }

    @Override
    public Color getById(Long id) {
        return repository.findById(id);
    }
}

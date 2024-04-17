package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.PostView;
import com.newlecmineursprj.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;

    @Override
    public List<PostView> getList() {
        return repository.findAll();
    }

    @Override
    public PostView getById(Long id) {
        return repository.findById(id);
    }
}

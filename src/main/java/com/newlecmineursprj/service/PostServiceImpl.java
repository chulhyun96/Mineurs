package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Post;
import com.newlecmineursprj.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository repository;
    @Override
    public List<Post> getList() {
        return repository.findAll();
    }
}

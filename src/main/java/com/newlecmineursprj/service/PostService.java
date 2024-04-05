package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Post;

import java.util.List;

public interface PostService {
    List<Post> getPostList();

    Post getPostById(Long id);
}

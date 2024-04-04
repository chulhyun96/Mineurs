package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    List<Post> findAll();
}

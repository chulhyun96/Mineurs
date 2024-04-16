package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.entity.PostView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostRepository {

    List<PostView> findAll();

    PostView findById(Long id);
}

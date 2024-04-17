package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.entity.PostView;

import java.util.List;

public interface PostService {

    List<PostView> getList();

    PostView getById(Long id);
}

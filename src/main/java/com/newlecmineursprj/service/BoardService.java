package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> getPostList();

    Board getPostById(Long id);
}

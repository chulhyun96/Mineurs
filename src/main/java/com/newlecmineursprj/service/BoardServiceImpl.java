package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Board;
import com.newlecmineursprj.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository repository;
    @Override
    public List<Board> getPostList() {
        return repository.findAll();
    }

    @Override
    public Board getPostById(Long id) {
        return repository.findById(id);
    }
}

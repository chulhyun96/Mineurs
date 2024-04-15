package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    List<Board> findAll();

    Board findById(Long id);
}

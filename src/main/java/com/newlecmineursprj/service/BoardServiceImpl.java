package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository repository;

    @Override
    public List<QnaView> getList(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o) {
        return repository.findAll(page,searchMethod,searchKeyword,categoryId,o);
    }

    @Override
    public Qna getById(Long id) {
        return repository.findById(id);
    }
}

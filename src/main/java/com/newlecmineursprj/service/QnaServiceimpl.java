package com.newlecmineursprj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.repository.QnaRepository;

@Service
public class QnaServiceimpl implements QnaService {

    @Autowired
    private QnaRepository repository;

    @Override
    public List<Qna> getList() {
        return repository.findAll();
    }

    @Override
    public Qna getById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Qna> getListByMemberId(long memberId) {
        return repository.findAllByMemberId(memberId);
    }
    
}

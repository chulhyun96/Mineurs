package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaServiceimpl implements QnaService {

    @Autowired
    private final QnaRepository repository;

    @Override
    public List<Qna> getList() {
        return repository.findAll();
    }

    @Override
    public List<QnaView> getList(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o) {
        return repository.findAll(page,searchMethod,searchKeyword,categoryId,null);
    }

    @Override
    public void reg(Qna qna) {
        repository.save(qna);
    }

    @Override
    public Qna getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Qna> getListByMemberId(long memberId) {
        return repository.findAllByMemberId(memberId);
    }

    @Override
    public void increase(Long id) {
        repository.update(id);
    }

    @Override
    public int getByPassword(Long id, String password) {
        return repository.findByPassword(id, password);
    }

//    @Override
//    public int getByPassword(Long id, String password) {
//        int result = repository.findByPassword(id,password);
////        if(qna.getPassword().equals(password))
////            return 0;
//    }

}

package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;

import java.util.List;

public interface QnaService {
    List<Qna> getList();

    List<QnaView> getList(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o);

    void reg(Qna qna);
    Qna getById(Long id);
    List<Qna> getListByMemberId(long memberId);

    void increase(Long id);

    int getByPassword(Long id, String password);
}

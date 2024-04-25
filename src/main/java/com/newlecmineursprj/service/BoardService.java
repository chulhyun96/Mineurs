package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaView;

import java.util.List;

public interface BoardService {
    List<QnaView> getList(Integer page, String searchMethod, String searchKeyword, Integer categoryId, Object o);

    Qna getById(Long id);
}

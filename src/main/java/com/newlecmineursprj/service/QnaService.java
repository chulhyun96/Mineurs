package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.Qna;

public interface QnaService {
    List<Qna> getList();
    Qna getById(long id);
    List<Qna> getListByMemberId(long memberId);
}

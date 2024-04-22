package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> findAll();

    Notice findById(Long id);

    void reg(Notice notice, Long memberId);
}

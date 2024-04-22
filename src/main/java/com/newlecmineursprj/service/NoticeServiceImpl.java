package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Notice;
import com.newlecmineursprj.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository repository;
    @Override
    public List<Notice> findAll() {
        return repository.findAll();
    }

    @Override
    public Notice findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void reg(Notice notice, Long memberId) {
        repository.reg(notice, memberId);
    }
}

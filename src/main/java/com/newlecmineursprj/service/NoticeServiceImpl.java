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
}

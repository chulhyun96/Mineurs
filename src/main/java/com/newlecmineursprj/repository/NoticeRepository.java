package com.newlecmineursprj.repository;

import com.newlecmineursprj.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeRepository {

    List<Notice> findAll();

    Notice findById(Long id);
}

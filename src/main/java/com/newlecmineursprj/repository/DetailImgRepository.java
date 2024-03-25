package com.newlecmineursprj.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.newlecmineursprj.entity.DetailImg;

@Mapper
public interface DetailImgRepository {

    void reg(List<DetailImg> dimgs);
}

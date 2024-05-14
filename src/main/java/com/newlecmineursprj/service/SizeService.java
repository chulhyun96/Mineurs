package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> getList(long productId);
    Size getById(Long id);

    List<Size> getListByKorName(String query);
    Long getIdByEngName(String engName);
}

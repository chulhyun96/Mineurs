package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> getList(long productId);
}

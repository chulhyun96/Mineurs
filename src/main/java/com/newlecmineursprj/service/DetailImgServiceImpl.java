package com.newlecmineursprj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.DetailImg;
import com.newlecmineursprj.repository.DetailImgRepository;

@Service
public class DetailImgServiceImpl implements DetailImgService {

    @Autowired
    private DetailImgRepository repository;

    @Override
    public void reg(String paths, Long productId) {
        String[] pathsArr = paths.split(",");

        List<DetailImg> dimgs = new ArrayList<>();
        for (int i = 0; i < pathsArr.length; i++) {
            DetailImg dimg = new DetailImg();

            dimg.setPath(pathsArr[i]);
            dimg.setProductId(productId);
            dimgs.add(dimg);
        }
    }

    @Override
    public void regAll(String paths, Long productId) {

        String[] pathsArr = paths.split(",");

        List<DetailImg> dimgs = new ArrayList<>();

        for (int i = 0; i < pathsArr.length; i++) {
            DetailImg dimg = new DetailImg();

            dimg.setPath(pathsArr[i]);
            dimg.setProductId(productId);
            dimgs.add(dimg);
        }

        repository.reg(dimgs);
    }

}

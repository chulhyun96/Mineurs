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
    public void regAll(String paths, Long productId) {
        List<DetailImg> dimgs = splitPath(paths, productId);
        repository.reg(dimgs);
    }

    private List<DetailImg> splitPath(String paths, Long productId) {
        String[] pathsArr = paths.split(",");
        List<DetailImg> dimgs = new ArrayList<>();

        for (String path : pathsArr) {
            DetailImg dimg = new DetailImg();

            dimg.setPath(path);
            dimg.setProductId(productId);
            dimgs.add(dimg);
        }
        return dimgs;
    }
}

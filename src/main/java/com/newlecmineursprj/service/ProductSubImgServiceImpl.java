package com.newlecmineursprj.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.repository.ProductSubImgRepository;

@Service
@RequiredArgsConstructor
public class ProductSubImgServiceImpl implements ProductSubImgService {
    private final ProductSubImgRepository repository;
    @Override
    public void regAll(String paths, Long productId) {
        List<ProductSubImg> dimgs = splitPath(paths, productId);
        repository.reg(dimgs);
    }
    private List<ProductSubImg> splitPath(String paths, Long productId) {
        String[] pathsArr = paths.split(",");
        List<ProductSubImg> dimgs = new ArrayList<>();

        for (String path : pathsArr) {
            ProductSubImg dimg = new ProductSubImg();

            dimg.setPath(path);
            dimg.setProductId(productId);
            dimgs.add(dimg);
        }
        return dimgs;
        /*return Arrays.stream(paths.split(","))
                .map(path -> ProductSubImg.builder()
                        .path(path)
                        .productId(productId)
                        .build())
                .collect(Collectors.toList());*/
    }
}

package com.newlecmineursprj.service;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.newlecmineursprj.entity.ProductSubImg;
import com.newlecmineursprj.repository.ProductSubImgRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSubImgServiceImpl implements ProductSubImgService {
    private final ProductSubImgRepository repository;
    public void regAll(MultipartFile[] multipartFiles, Long productId) {
        if (!isEmpty(multipartFiles)) {
            for (MultipartFile multipartFile : multipartFiles) {
                List<ProductSubImg> productSubImgList = new ArrayList<>();
                ProductSubImg productSubImg = ProductSubImg.builder()
                        .productId(productId)
                        .path(multipartFile.getOriginalFilename())
                        .build();
                productSubImgList.add(productSubImg);
                repository.reg(productSubImgList);
            }
        }
    }

    private static boolean isEmpty(MultipartFile[] multipartFiles) {
        return multipartFiles[0].isEmpty();
    }
}
package com.newlecmineursprj.service;

import org.springframework.web.multipart.MultipartFile;


public interface ProductSubImgService {
    void regAll(MultipartFile[] subImgs, Long productId);
}

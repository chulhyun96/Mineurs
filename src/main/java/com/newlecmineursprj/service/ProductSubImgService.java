package com.newlecmineursprj.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductSubImgService {
    void regAll(MultipartFile[] paths, Long productId);
}

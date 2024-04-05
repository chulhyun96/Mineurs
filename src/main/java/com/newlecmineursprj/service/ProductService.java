package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductView> getList(Integer page);
    List<ProductView> getList(Integer page, String searchMethod, String searchKeyword);
    ProductView getById(Long id);
    String saveProductImg(MultipartFile img, String realPath);
    void reg(Product product);
    void edit(Product product);
    void deleteAllById(List<Long> deleteId);
    int getCount();
}

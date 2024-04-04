package com.newlecmineursprj.service;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductView> getList(Integer page);
    List<ProductView> getList(Integer page, String searchMethod, String searchKeyword);
    void reg(Product product);
    ProductView getById(Long id);
    void edit(Product product);
    void deleteAllById(List<Long> deleteId);
    String saveProductImg(MultipartFile img, String realPath);
    int getCount();
}

package com.newlecmineursprj.service;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<ProductListDTO> getList(Integer page, String searchMethod, String searchKeyword, long categoryId);
    Product getById(Long id);
    void reg(Product product,MultipartFile mainImg ,List<MultipartFile> subImgs) throws IOException;
    void update(Product updateProduct, MultipartFile updateFile, List<MultipartFile> updateSubImgs) throws IOException;
    void deleteAllById(List<Long> deleteId);
    int getCount(String searchMethod, String searchKeyword, long categoryId);
}

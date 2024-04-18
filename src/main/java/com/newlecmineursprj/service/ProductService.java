package com.newlecmineursprj.service;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.dto.ProductRegDTO;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductView;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductListDTO> getList(Integer page, String searchMethod, String searchKeyword, long categoryId);
    Product getById(Long id);
    void reg(Product product,MultipartFile mainImg ,List<MultipartFile> subImgs) throws IOException;
    void edit(ProductView product);
    void deleteAllById(List<Long> deleteId);
    int getCount(String searchMethod, String searchKeyword, long categoryId);
}

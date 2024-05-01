package com.newlecmineursprj.service;

import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.util.CustomPageImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    CustomPageImpl<ProductListDTO> getList(Integer pageNumber, Integer pageSize, String sortMethod,
            Integer pageGroupSize, String searchMethod, String searchKeyword, long categoryId, String startDate,
            String endDate);

    CustomPageImpl<ProductListDTO> getList(Integer pageNumber, Integer pageSize, String sortMethod,
            String sortDirection, Integer pageGroupSize, String searchMethod, String searchKeyword, long categoryId,
            String startDate, String endDate);

    Product getById(Long id);
    void reg(Product product,MultipartFile mainImg ,List<MultipartFile> subImgs) throws IOException;
    void update(Product updateProduct, MultipartFile updateFile, List<MultipartFile> updateSubImgs) throws IOException;
    void deleteAllById(List<Long> deleteId);
    int getCount(String searchMethod, String searchKeyword, long categoryId);
    int updateAll(List<Product> products);
    public CustomPageImpl<ProductListDTO> getWishList(Integer pageNumber, Integer pageSize, Integer pageGroupSize,
            long memberId);
}
